package com.schedguap.schedguap.Services;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.schedguap.schedguap.Entities.Deadline;
import com.schedguap.schedguap.Entities.DeadlineSource;
import com.schedguap.schedguap.Entities.ProGuapTask;
import com.schedguap.schedguap.Entities.Repositories.SubjectRepository;
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
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProguapService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Set<DeadlineSource> getDeadlineSources(String cookie) throws UserException {
        List<ProGuapTask> tasks = fetchAllDeadlines(cookie).getTasks();

        return tasks.stream().map( task -> task.getSource()).collect(Collectors.toSet());
    }

    public List<Deadline> getDeadlines(String cookie, String sourceId) throws UserException {

        List<ProGuapTask> tasks = fetchAllDeadlines(cookie).getTasks();

        return tasks.stream()
                .filter( task ->  {
                    if( sourceId != null && !sourceId.isEmpty() ) return task.getSource().getId().equals(sourceId);
                    else return true;
                })
                .map( task -> new Deadline(task, subjectRepository))
                .collect(Collectors.toList());
    }

    private DeadlinesResponse fetchAllDeadlines(String cookie) throws UserException {
        // Сюда мы добавляем кэширование в редисе, ключ - кука

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookie);
        headers.set("Sec-Fetch-Mode", "cors");
        HttpEntity entity = new HttpEntity(headers);

        // fetch user_id
        String userId = fetchUserId(cookie);

        String url =  "https://pro.guap.ru/get-student-tasksdictionaries/?iduser="+ userId;
        ResponseEntity<DeadlinesResponse> deadlines = new RestTemplate().exchange(url, HttpMethod.POST, entity, DeadlinesResponse.class);
        return deadlines.getBody();

    }

    private String fetchUserId(String cookie) throws UserException {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookie);
        headers.set("Sec-Fetch-Mode", "cors");
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<String> userInfoEntity = new RestTemplate().exchange("https://pro.guap.ru/inside_s", HttpMethod.GET, entity, String.class);

        String userInfo = userInfoEntity.getBody();
        String start = "window.__initialServerData = ";
        if (userInfo != null) {
            userInfo = userInfo.substring(userInfo.indexOf(start) + start.length());
            userInfo = userInfo.substring(0, userInfo.indexOf(';'));
        }

        try {
            JSONObject userInfoGson = new JSONObject(userInfo);
            JSONObject userGuap = userInfoGson.getJSONArray("user").getJSONObject(0);
            return userGuap.get("user_id").toString();
        } catch (JSONException | NullPointerException e) {
            throw new UserException(UserExceptionType.FORBIDDEN, "Failed to get current user id");
        }
    }
}

class DeadlinesResponse {

    @JsonProperty
    private List<ProGuapTask> tasks;

    public List<ProGuapTask> getTasks() {
        return tasks;
    }
}

