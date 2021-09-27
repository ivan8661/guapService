package com.schedguap.schedguap.Services;


import com.schedguap.schedguap.Entities.DeadlinesResponse;
import com.schedguap.schedguap.Entities.Deadline;
import com.schedguap.schedguap.Entities.DeadlineSource;
import com.schedguap.schedguap.Entities.ProGuapTask;
import com.schedguap.schedguap.Repositories.SubjectRepository;
import com.schedguap.schedguap.Exceptions.UserException;
import com.schedguap.schedguap.Exceptions.UserExceptionType;
import com.schedguap.schedguap.SchedguapApplication;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ProguapService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private RedisTemplate<String, String> userIdTemplate;

    @Autowired
    private RedisTemplate<String, DeadlinesResponse> proguapTaskListTemplate;

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

    public Deadline getDeadline(String cookie, String deadlineId) throws UserException {
        // это прям очень плохое решение, при запросе единичного надо не ходить в кэш, а по id получать из прогуапа ( там больше информации)
        List<ProGuapTask> tasks = fetchAllDeadlines(cookie).getTasks();

        Optional<Deadline> deadline = tasks
                .stream()
                .filter( x-> x.getId().equals(deadlineId))
                .map(x -> new Deadline(x, subjectRepository))
                .findFirst();

        if(deadline.isEmpty()) {
            throw new UserException(UserExceptionType.OBJECT_NOT_FOUND);
        } else {
            return  deadline.get();
        }
    }

    private DeadlinesResponse fetchAllDeadlines(String cookie) throws UserException {
        // Сюда мы добавляем кэширование в редисе, ключ - кука
        SchedguapApplication.getLog().info("fetching deadlines for cookie="+cookie);
        DeadlinesResponse cachedResponse = proguapTaskListTemplate.boundValueOps(cookie).get();
        if (cachedResponse != null) {
            SchedguapApplication.getLog().info("returning cached deadlines for cookie="+cookie);
            return cachedResponse;
        }
        SchedguapApplication.getLog().info("no cached deadlines found for cookie="+cookie);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookie);
        headers.set("Sec-Fetch-Mode", "cors");
        HttpEntity entity = new HttpEntity(headers);

        // fetch user_id
        String userId = fetchUserId(cookie);

        String url =  "https://pro.guap.ru/get-student-tasksdictionaries/?iduser="+ userId;
        ResponseEntity<DeadlinesResponse> response = new RestTemplate().exchange(url, HttpMethod.POST, entity, DeadlinesResponse.class);
        DeadlinesResponse deadlines = response.getBody();
        proguapTaskListTemplate.boundValueOps(cookie).set(deadlines, 15, TimeUnit.MINUTES);
        return deadlines;

    }

    public String fetchUserId(String cookie) throws UserException {
        SchedguapApplication.getLog().info("fetching user id");

        String cachedUserId = userIdTemplate.boundValueOps(cookie).get();
        if( cachedUserId != null ) {
            SchedguapApplication.getLog().info("returning cached user_id="+cachedUserId+" for cookie="+cookie);
            return cachedUserId;
        }
        SchedguapApplication.getLog().info("no cached user_id found for cookie="+cookie);


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

            String fetchedUserId = userGuap.get("user_id").toString();

            SchedguapApplication.getLog().info("setting cached user_id="+fetchedUserId+" for cookie="+cookie);
            userIdTemplate.boundValueOps(cookie).set(fetchedUserId, 3, TimeUnit.DAYS);
            SchedguapApplication.getLog().info("did set cached user_id="+userIdTemplate.boundValueOps(cookie).get()+" for cookie="+cookie);
            return fetchedUserId;

        } catch (JSONException | NullPointerException e) {
            throw new UserException(UserExceptionType.FORBIDDEN, "Failed to get current user id");
        }
    }
}