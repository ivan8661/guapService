package com.schedguap.schedguap.Controllers;


import com.schedguap.schedguap.Exceptions.UserException;
import com.schedguap.schedguap.Exceptions.UserExceptionType;
import com.schedguap.schedguap.Services.DataImport.ImportService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
@RequestMapping("guap")
public class UtilController {

    @Autowired
    private ImportService importService;


    @GetMapping(path="/import")
    public ResponseEntity<String> authVK(@RequestBody String password) throws JSONException, UserException {
        JSONObject passwordObject = new JSONObject(password);
        if(!passwordObject.has("password")) {
            throw new UserException(UserExceptionType.FORBIDDEN, null, null);
        } else {
            importService.downloadData();
            importService.downloadLessons();
        }
        return ResponseEntity.ok().body("successful");
    }

}
