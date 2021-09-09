package com.schedguap.schedguap.Controllers;


import com.schedguap.schedguap.Entities.News;
import com.schedguap.schedguap.SchedguapApplication;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

@RestController
@ControllerAdvice
public class UniversityInfoController {
    
    @GetMapping("/universityInfo")
    public ResponseEntity<String> universityInfo() throws JSONException {

        JSONObject universityInfo = new JSONObject();

        universityInfo.put("_id", "GUAP");
        universityInfo.put("name", "ГУАП");
        universityInfo.put("serviceName", "pro.guap");
        universityInfo.put("referenceDate", SchedguapApplication.SYNC_TIME);
        universityInfo.put("referenceWeek", SchedguapApplication.CURRENT_WEEK);

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
