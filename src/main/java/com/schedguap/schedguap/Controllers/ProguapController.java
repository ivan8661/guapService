package com.schedguap.schedguap.Controllers;


import com.schedguap.schedguap.Entities.Deadline;
import com.schedguap.schedguap.Entities.DeadlineSource;
import com.schedguap.schedguap.Entities.ListAnswer;
import com.schedguap.schedguap.Exceptions.UserException;
import com.schedguap.schedguap.Exceptions.UserExceptionType;
import com.schedguap.schedguap.Services.ProguapService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@ControllerAdvice
public class ProguapController {


    @Autowired
    private ProguapService proguapService;

    @PostMapping("/deadlineSources")
    public ResponseEntity<ListAnswer<DeadlineSource>> deadlineSources(@RequestBody String body) throws JSONException, UserException {
        JSONObject jsonBody = new JSONObject(body);
        String userId, userCookie;
        if(jsonBody.optString("cookie") != null){
            userCookie = jsonBody.optString("cookie");

            List<DeadlineSource> sources = proguapService.getDeadlineSources(userCookie).stream().collect(Collectors.toList());
            return ResponseEntity.ok().body(new ListAnswer(sources, sources.size()));
        } else {
            throw new UserException(UserExceptionType.BAD_REQUEST);
        }
    }

    @PostMapping("/deadlines")
    public ResponseEntity<ListAnswer<Deadline>> deadlines(@RequestBody String body) throws JSONException, UserException {
        JSONObject jsonBody = new JSONObject(body);

        String userCookie = jsonBody.optString("cookie");
        String sourceId = jsonBody.optString("sourceId");

        System.out.println(sourceId);

        if(userCookie != null) {
            List<Deadline> sources = proguapService.getDeadlines(userCookie, sourceId);
            return ResponseEntity.ok().body(new ListAnswer(sources, sources.size()));
        } else {
            throw new UserException(UserExceptionType.BAD_REQUEST);
        }
    }

    @PostMapping("/deadlines/{deadlineId}")
    public ResponseEntity<Deadline> deadlines(@RequestBody String body, @PathVariable ("deadlineId") String deadlineId) throws JSONException, UserException {
        JSONObject jsonBody = new JSONObject(body);

        String userCookie = jsonBody.optString("cookie");
        String sourceId = jsonBody.optString("sourceId");


        if(userCookie != null && deadlineId != null) {
            Deadline deadline = proguapService.getDeadline(userCookie, deadlineId);
            return ResponseEntity.ok().body(deadline);
        } else {
            throw new UserException(UserExceptionType.BAD_REQUEST);
        }
    }

}
