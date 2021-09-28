package com.schedguap.schedguap.Services;


import com.schedguap.schedguap.Entities.DatabaseEntities.PupilGroup;
import com.schedguap.schedguap.Entities.User;
import com.schedguap.schedguap.Repositories.PupilGroupRepository;
import com.schedguap.schedguap.Exceptions.UserException;
import com.schedguap.schedguap.Exceptions.UserExceptionType;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class AuthorizationService {

    private PupilGroupRepository pupilGroupRepository;

    @Autowired
    private ProguapService guapService;

    @Autowired
    public AuthorizationService(PupilGroupRepository pupilGroupRepository) {
        this.pupilGroupRepository = pupilGroupRepository;
    }

    public User regGUAPUser(String login, String password) throws UserException, JSONException {

        if(login == null || password == null){
            throw new UserException(UserExceptionType.BAD_REQUEST, null, null);
        }

        String cookie = getCookie(login, password);

        String userId = guapService.fetchUserId(cookie);
        User user = fetchUserInfo(userId, cookie);

        return user;
    }

    private User fetchUserInfo(String userId, String cookie) throws JSONException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", cookie);
        ResponseEntity<String> response = new RestTemplate().exchange("https://pro.guap.ru/getstudentprofile/" + userId, HttpMethod.GET, new HttpEntity<>(httpHeaders), String.class);

        JSONObject json = new JSONObject(response.getBody());
        JSONObject userJson = json.getJSONObject("user");

        String groupName = userJson.getString("grnum");
        PupilGroup pupilGroup = null;

        Optional<PupilGroup> optGroup = pupilGroupRepository.findPupilGroupByName(groupName);
        if(optGroup.isPresent()) {
            pupilGroup = optGroup.get();
        }

        return new User(
                userJson.optString("firstname"),
                userJson.optString("lastname"),
                userJson.optString("image"),
                userJson.optString("id"),
                cookie,
                pupilGroup.getId()
        );

    }


    public String getCookie(String login, String password) throws UserException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Sec-Fetch-Site", "same-origin");
        headers.set("Accept-Encoding", "gzip, deflate, br");
        headers.set("Host", "pro.guap.ru");
        headers.set("Sec-Fetch-Mode", "navigate");
        headers.set("Sec-Fetch-User", "?1");
        headers.set("Accept-Language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.set("Sec-Fetch-Dest", "document");
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.116 Safari/537.36 OPR/67.0.3575.130");
        headers.set("Accept", "*/*");
        headers.set("Connection", "keep-alive");
        headers.set("Upgrade-Insecure-Requests", "1");
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<String>guapAnswer = new RestTemplate().exchange("https://pro.guap.ru/exters/", HttpMethod.GET, entity, String.class);

        String cookie = guapAnswer.getHeaders().getFirst(HttpHeaders.SET_COOKIE);

        if(cookie != null) {
            cookie = cookie.substring(0, cookie.indexOf(';'));
        } else {
            throw new UserException(UserExceptionType.VALIDATION_ERROR, "incorrect input data", null);
        }

        headers.set("Cookie", cookie);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("_username", login);
        params.add("_password", password);


        /*get second cookie*/
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<String> cookie2 = new RestTemplate().exchange("https://pro.guap.ru/user/login_check", HttpMethod.POST, request, String.class);
        String secondCookie = cookie2.getHeaders().getFirst(HttpHeaders.SET_COOKIE);

        if(secondCookie == null)
            throw new UserException(UserExceptionType.VALIDATION_ERROR, "неверный логин или пароль", null);

        secondCookie = secondCookie.substring(0, secondCookie.indexOf(";"));

        return secondCookie;
    }
}
