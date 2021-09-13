package com.schedguap.schedguap.Controllers;


import com.schedguap.schedguap.Exceptions.UserException;
import com.schedguap.schedguap.Exceptions.UserExceptionType;
import com.schedguap.schedguap.Services.DataImport.ImportService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
@RequestMapping("guap")
@EnableScheduling
public class UtilController {

    @Autowired
    private ImportService importService;


    @GetMapping(path="/import")
    public ResponseEntity<String> importEndPoint(@RequestBody String password) throws JSONException, UserException {
        JSONObject passwordObject = new JSONObject(password);
        if(!passwordObject.has("password")) {
            throw new UserException(UserExceptionType.FORBIDDEN, null, null);
        } else {
            importService.downloadData();
            importService.downloadLessons();
            importService.updateScheduleInfo();
        }
        return ResponseEntity.ok().body("successful");
    }

}
