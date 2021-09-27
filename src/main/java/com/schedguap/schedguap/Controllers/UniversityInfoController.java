package com.schedguap.schedguap.Controllers;


import com.schedguap.schedguap.Entities.DatabaseEntities.ScheduleUpdate;
import com.schedguap.schedguap.Entities.News;
import com.schedguap.schedguap.Repositories.ScheduleUpdateRepository;
import com.schedguap.schedguap.Exceptions.UserException;
import com.schedguap.schedguap.Exceptions.UserExceptionType;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

@RestController
@ControllerAdvice
public class UniversityInfoController {


    private ScheduleUpdateRepository scheduleUpdateRepository;


    @Autowired
    public UniversityInfoController(ScheduleUpdateRepository scheduleUpdateRepository) {
            this.scheduleUpdateRepository = scheduleUpdateRepository;
    }

    @GetMapping("/universityInfo")
    public ResponseEntity<String> universityInfo() throws JSONException, UserException {

        JSONObject universityInfo = new JSONObject();
        ScheduleUpdate scheduleUpdate = scheduleUpdateRepository.findByName("GUAP");
        if(scheduleUpdate==null)
            throw new UserException(UserExceptionType.OBJECT_NOT_FOUND, null, null);


        universityInfo.put("_id", "GUAP");
        universityInfo.put("name", "ГУАП");
        universityInfo.put("serviceName", "pro.guap");
        universityInfo.put("referenceDate", scheduleUpdate.getSyncTime());
        universityInfo.put("referenceWeek", scheduleUpdate.getWeek());

        return ResponseEntity.ok().body(universityInfo.toString());
    }

    @GetMapping("/newsSources")
    public ResponseEntity<LinkedList<News>> sourceInfo() {

        LinkedList<News> news = new LinkedList<>();
        news.add(new News("-122496494", "ГУАП | SUAI", true, false));
        news.add(new News("-185361762", "Аэрокосмический ор", true, true));
        news.add(new News("-31327829", "Типичный ГУАП", true, false));
        news.add(new News("-58741668", "Подслушано ГУАП", true, true));

        return ResponseEntity.ok().body(news);
    }


}
