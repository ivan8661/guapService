package com.schedguap.schedguap.Controllers;


import com.schedguap.schedguap.Entities.DatabaseEntities.Lesson;
import com.schedguap.schedguap.Entities.DatabaseEntities.Subject;
import com.schedguap.schedguap.Exceptions.UserException;
import com.schedguap.schedguap.Services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@ControllerAdvice
public class ScheduleController {


    private ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/schedule/{scheduleUserId}")
    public ResponseEntity<List<Lesson>> lessons(@PathVariable("scheduleUserId") String scheduleUserId) throws UserException {
        return ResponseEntity.ok().body(scheduleService.getLessons(scheduleUserId));
    }

    @GetMapping("lessons/{lessonId}")
    public ResponseEntity<Lesson> lesson(@PathVariable("lessonId") String lessonId) throws UserException {
        return ResponseEntity.ok().body(scheduleService.getLesson(lessonId));
    }

    @GetMapping("subjects/{subjectId}")
    public ResponseEntity<Subject> subject(@PathVariable("subjectId") String subjectId) throws UserException {
        return ResponseEntity.ok().body(scheduleService.getSubject(subjectId));
    }
    @PostMapping("subjects")
    public ResponseEntity<List<Subject>> subjects(@RequestBody Set<String> subjects) {
        return ResponseEntity.ok().body(scheduleService.getSubjects(subjects));
    }
}
