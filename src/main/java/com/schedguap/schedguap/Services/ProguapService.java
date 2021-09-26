package com.schedguap.schedguap.Services;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.schedguap.schedguap.Entities.DeadlineSource;
import com.schedguap.schedguap.Entities.ProGuapTask;
import com.schedguap.schedguap.Exceptions.UserException;
import com.schedguap.schedguap.Exceptions.UserExceptionType;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONException;
import org.json.JSONObject;
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

    public List<DeadlineSource> getDeadlineSources(String userId, String cookie) {


        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookie);
        headers.set("Sec-Fetch-Mode", "cors");
        HttpEntity entity = new HttpEntity(headers);

        String url =  "https://pro.guap.ru/get-student-tasksdictionaries/?iduser="+ userId;
        ResponseEntity<DeadlinesResponse> deadlines = new RestTemplate().exchange(url, HttpMethod.POST, entity, DeadlinesResponse.class);


        Set<String> names = new HashSet<>();
        for(ProGuapTask task: deadlines.getBody().getTasks()){
            String subjectName = task.getSubjectName();
            if(subjectName != null && !names.contains(subjectName)){
                names.add(subjectName);
            }
        }
        return names.stream().map( name -> new DeadlineSource(name)).collect(Collectors.toList());
    }

}

class DeadlinesResponse {

    @JsonProperty
    private List<ProGuapTask> tasks;

    public List<ProGuapTask> getTasks() {
        return tasks;
    }
}

