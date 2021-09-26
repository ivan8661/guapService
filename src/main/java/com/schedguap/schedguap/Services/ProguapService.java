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

    public Set<DeadlineSource> getDeadlineSources(String userId, String cookie) {
        List<ProGuapTask> tasks = fetchAllDeadlines(userId, cookie).getTasks();

        return tasks.stream().map( task -> task.getSource()).collect(Collectors.toSet());
    }

    public List<Deadline> getDeadlines(String userId, String cookie, String sourceId) {

        List<ProGuapTask> tasks = fetchAllDeadlines(userId, cookie).getTasks();

        return tasks.stream()
                .filter( task ->  {
                    if( sourceId != null && !sourceId.isEmpty() ) return task.getSource().getId().equals(sourceId);
                    else return true;
                })
                .map( task -> new Deadline(task, subjectRepository))
                .collect(Collectors.toList());
    }

    private DeadlinesResponse fetchAllDeadlines(String userId, String cookie) {

        // Сюда мы добавляем кэширование в редисе, ключ - кука
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookie);
        headers.set("Sec-Fetch-Mode", "cors");
        HttpEntity entity = new HttpEntity(headers);

        String url =  "https://pro.guap.ru/get-student-tasksdictionaries/?iduser="+ userId;
        ResponseEntity<DeadlinesResponse> deadlines = new RestTemplate().exchange(url, HttpMethod.POST, entity, DeadlinesResponse.class);
        return deadlines.getBody();
    }
}

class DeadlinesResponse {

    @JsonProperty
    private List<ProGuapTask> tasks;

    public List<ProGuapTask> getTasks() {
        return tasks;
    }
}

