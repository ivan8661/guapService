package com.schedguap.schedguap.Services;


import com.schedguap.schedguap.Entities.DeadlineSource;
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

@Service
public class ProguapService {

    public Set<DeadlineSource> getDeadlineSources(String body) throws JSONException, UserException {

        JSONObject jsonBody = new JSONObject(body);
        String userId, userCookie;
        if(jsonBody.optString("userId") != null && jsonBody.optString("cookie") != null){
            userId = jsonBody.optString("userId");
            userCookie = jsonBody.optString("cookie");
        } else {
            throw new UserException(UserExceptionType.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", userCookie);
        headers.set("Sec-Fetch-Mode", "cors");

        ResponseEntity<String> deadlineAnswer = new RestTemplate().exchange("https://pro.guap.ru/get-student-tasksdictionaries/?iduser="+ userId + "&role=1&tid=0",
                HttpMethod.POST, new HttpEntity(headers), String.class);

        JSONObject deadlines = new JSONObject(deadlineAnswer.getBody());

        Set<DeadlineSource> sources = new HashSet<>();

        for(int i = 0; i < deadlines.getJSONArray("tasks").length()-1; ++i){
            JSONObject tmp = deadlines.getJSONArray("tasks").getJSONObject(i);
            String subjectName = tmp.optString("subject_name");
            if(subjectName != null){
                sources.add(new DeadlineSource(subjectName));
            }
        }
        return sources;
    }

}
