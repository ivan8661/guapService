package com.schedguap.schedguap.Services;


import com.schedguap.schedguap.Entities.DatabaseEntities.PupilGroup;
import com.schedguap.schedguap.Entities.Repositories.PupilGroupRepository;
import com.schedguap.schedguap.Exceptions.UserException;
import com.schedguap.schedguap.Exceptions.UserExceptionType;
import org.apache.commons.codec.digest.DigestUtils;
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

@Service
public class AuthorizationService {

    private PupilGroupRepository pupilGroupRepository;


    @Autowired
    public AuthorizationService(PupilGroupRepository pupilGroupRepository) {
        this.pupilGroupRepository = pupilGroupRepository;
    }

    public String regGUAPUser(String regData) throws UserException, JSONException {

        JSONObject reg = new JSONObject(regData);
        String cookie;
        String login = reg.optString("serviceLogin");
        String password = reg.optString("servicePassword");

        if(login == null || password == null){
            throw new UserException(UserExceptionType.BAD_REQUEST, null, null);
        } else {
            cookie = getCookie(login, password);
        }

        HttpHeaders httpHeaders =new HttpHeaders();
        httpHeaders.add("Cookie", cookie);

        ResponseEntity<String> userInfoEntity = new RestTemplate().exchange("https://pro.guap.ru/inside_s",
                HttpMethod.GET, new HttpEntity<>(httpHeaders), String.class);



        String userInfo = userInfoEntity.getBody();
        String start = "window.__initialServerData = ";
        if (userInfo != null) {
            userInfo = userInfo.substring(userInfo.indexOf(start) + start.length());
            userInfo = userInfo.substring(0, userInfo.indexOf(';'));
        }
        JSONObject userInfoGson = new JSONObject(userInfo);
        JSONObject userGuap = userInfoGson.getJSONArray("user").getJSONObject(0);

        /*
         * get group through query to pro.guap by id
         */
        ResponseEntity<String> userAnswer = new RestTemplate().exchange("https://pro.guap.ru/getstudentprofile/" + userGuap.get("user_id"),
                HttpMethod.GET, new HttpEntity<>(httpHeaders), String.class);
        JSONObject userAnswerGson = new JSONObject(userAnswer.getBody());
        String numberGroup = userAnswerGson.getJSONObject("user").getString("grnum");
        /*_________________________________________*/

        JSONObject user = new JSONObject();

        user.put("_id", DigestUtils.sha256Hex(login));
        user.put("serviceLogin", login);
        user.put("servicePassword", password);
        user.put("lastname", userGuap.optString("lastname"));
        user.put("firstname", userGuap.optString("firstname"));
        user.put("cookie", cookie);
        user.put("universityId", "GUAP");
        if(pupilGroupRepository.findPupilGroupByName(numberGroup).isPresent()) {
            PupilGroup pupilGroup = pupilGroupRepository.findPupilGroupByName(numberGroup).get();
            user.put("groupId", pupilGroup.getId());
            user.put("groupName", pupilGroup.getName());
        }

        return user.toString();
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
