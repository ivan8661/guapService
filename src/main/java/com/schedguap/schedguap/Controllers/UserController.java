package com.schedguap.schedguap.Controllers;


import com.schedguap.schedguap.Exceptions.UserException;
import com.schedguap.schedguap.Services.AuthorizationService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
public class UserController {


    private AuthorizationService authorizationService;

    @Autowired
    public UserController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping("/auth")
    public ResponseEntity<String> regUser(@RequestBody String regData) throws JSONException, UserException {
        return ResponseEntity.ok().body(authorizationService.regGUAPUser(regData));
    }
}
