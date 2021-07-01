package com.schedguap.schedguap.Controllers;


import com.schedguap.schedguap.Exceptions.UserException;
import com.schedguap.schedguap.Services.DataImport.ImportService;
import jdk.jshell.spi.ExecutionControl;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Map;

@ControllerAdvice
@RestController
@RequestMapping("guap")
public class AuthController {


    @Autowired
    private ImportService importService;

    @PostMapping(path = "/auth")
    public ResponseEntity<AnswerTemplate> authVK(@RequestBody String VKAuthData, @RequestHeader Map<String, String> headers)  {


        return ResponseEntity.ok().body(new AnswerTemplate(null, null));
    }

    @GetMapping(path="/import")
    public ResponseEntity<AnswerTemplate> authVK(@RequestBody String password) throws JSONException, UserException {
        JSONObject passwordObject = new JSONObject(password);
        if(!passwordObject.has("password")) {
            throw new UserException(403, "Forbidden", "ты чо сделать пытаешься, а?", "");
        } else {
            importService.downloadData();
            importService.downloadLessons();
        }

        return ResponseEntity.ok().body(new AnswerTemplate("successful", null));
    }




}
