package com.schedguap.schedguap.Controllers;


import com.schedguap.schedguap.Entities.DatabaseEntities.Lesson;
import com.schedguap.schedguap.Exceptions.UserException;
import com.schedguap.schedguap.Services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ControllerAdvice
public class ScheduleController {


    private ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/lessons/{scheduleUserId}")
    public ResponseEntity<List<Lesson>> lessons(@PathVariable("scheduleUserId") String scheduleUserId) throws UserException {
        return ResponseEntity.ok().body(scheduleService.getLessons(scheduleUserId));
    }
}
