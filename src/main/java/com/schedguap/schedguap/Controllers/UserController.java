package com.schedguap.schedguap.Controllers;


import com.schedguap.schedguap.Entities.User;
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
    public ResponseEntity<User> regUser(@RequestBody String regData) throws JSONException, UserException {
        JSONObject reg = new JSONObject(regData);
        String cookie;
        String login = reg.optString("serviceLogin");
        String password = reg.optString("servicePassword");
        User user = authorizationService.regGUAPUser(login, password);
        return ResponseEntity.ok().body(user);
    }
}
