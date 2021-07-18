package com.schedguap.schedguap.Controllers;


import com.schedguap.schedguap.Exceptions.UserException;
import com.schedguap.schedguap.Entities.ScheduleUser;
import com.schedguap.schedguap.Services.ScheduleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
public class ScheduleUserController {

    @Autowired
    private ScheduleUserService scheduleUserService;

    @GetMapping(path = "/scheduleUsers/{id}")
    public ResponseEntity<ScheduleUser> getScheduleUser(@PathVariable("id") String scheduleUserId) throws UserException {
        return ResponseEntity.ok().body(scheduleUserService.getScheduleUser(scheduleUserId));
    }



}
