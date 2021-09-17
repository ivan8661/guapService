package com.schedguap.schedguap.Controllers;


import com.schedguap.schedguap.Entities.DeadlineSource;
import com.schedguap.schedguap.Entities.ListAnswer;
import com.schedguap.schedguap.Exceptions.UserException;
import com.schedguap.schedguap.Services.ProguapService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@ControllerAdvice
public class ProguapController {


    @Autowired
    private ProguapService proguapService;

    @PostMapping("/deadlineSources")
    public ResponseEntity<Set<DeadlineSource>> deadlineSources(@RequestBody String body) throws JSONException, UserException {
        return ResponseEntity.ok().body(proguapService.getDeadlineSources(body));
    }


}
