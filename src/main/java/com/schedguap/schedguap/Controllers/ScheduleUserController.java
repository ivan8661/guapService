package com.schedguap.schedguap.Controllers;


import com.schedguap.schedguap.Exceptions.UserException;
import com.schedguap.schedguap.Services.ScheduleUser.ScheduleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
@RequestMapping("guap")
public class ScheduleUserController {

    @Autowired
    private ScheduleUserService scheduleUserService;

    @GetMapping(path = "/scheduleUsers/{id}")
    public ResponseEntity<AnswerTemplate<com.schedguap.schedguap.Services.ScheduleUser.ScheduleUser>> getScheduleUser(@PathVariable("id") String scheduleUserId) throws UserException {
        return ResponseEntity.ok().body(new AnswerTemplate<>(scheduleUserService.getScheduleUser(scheduleUserId), null));
    }
}
