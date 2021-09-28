package com.schedguap.schedguap.Services.DataImport;

import com.schedguap.schedguap.Entities.DatabaseEntities.*;
import com.schedguap.schedguap.Repositories.*;
import com.schedguap.schedguap.Exceptions.UserException;
import com.schedguap.schedguap.Exceptions.UserExceptionType;
import com.schedguap.schedguap.Services.DataImport.Entities.*;
import com.schedguap.schedguap.Services.GUAPUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ImportService {

    private final PupilGroupRepository pupilGroupRepository;
    private final BuildingRepository buildingRepository;
    private final LessonRepository lessonRepository;
    private final ProfessorsRepository professorsRepository;
    private final SubjectRepository subjectRepository;
    private final ScheduleUpdateRepository scheduleUpdateRepository;

    @Autowired
    public ImportService(PupilGroupRepository pupilGroupRepository, BuildingRepository buildingRepository,
                         LessonRepository lessonRepository, ProfessorsRepository professorsRepository,
                         SubjectRepository subjectRepository, ScheduleUpdateRepository scheduleUpdateRepository) {
        this.buildingRepository = buildingRepository;
        this.pupilGroupRepository = pupilGroupRepository;
        this.professorsRepository = professorsRepository;
        this.subjectRepository = subjectRepository;
        this.lessonRepository = lessonRepository;
        this.scheduleUpdateRepository = scheduleUpdateRepository;
    }

    @Transactional
    public boolean downloadData() throws UserException {

        HttpEntity entity = new HttpEntity(new HttpHeaders());
        ResponseEntity<List<BuildingEntity>> buildings = new RestTemplate().exchange("https://api.guap.ru/rasp/custom/get-sem-builds",
                HttpMethod.GET, entity, new ParameterizedTypeReference<>(){});
        ResponseEntity<List<SubjectEntity>> subjects = new RestTemplate().exchange("https://api.guap.ru/rasp/custom/get-sem-discs",
                HttpMethod.GET, entity, new ParameterizedTypeReference<>(){});
        ResponseEntity<List<GroupEntity>> groups = new RestTemplate().exchange("https://api.guap.ru/rasp/custom/get-sem-groups",
                HttpMethod.GET, entity, new ParameterizedTypeReference<>(){});
        ResponseEntity<List<ProffesorEntity>> professors = new RestTemplate().exchange("https://api.guap.ru/rasp/custom/get-sem-preps",
                HttpMethod.GET, entity, new ParameterizedTypeReference<>(){});

        if(buildings.getBody() == null || subjects.getBody() == null || groups.getBody() == null || professors.getBody() == null){
            throw new UserException(UserExceptionType.SERVER_ERROR,"не удалось подключиться к расписанию Гуапа",null);
        }

        for(BuildingEntity buildingGUAP : buildings.getBody()){
            Building building = new Building(DigestUtils.sha256Hex("guap_building" + buildingGUAP.getId()), buildingGUAP.getName());
            buildingRepository.save(building);
        }

        for(SubjectEntity subjectGUAP : subjects.getBody()){
            Subject subject = new Subject(DigestUtils.sha256Hex("guap_subject" + subjectGUAP.getId()), subjectGUAP.getName());
            subjectRepository.save(subject);
        }

        for(GroupEntity groupGUAP : groups.getBody()){
            PupilGroup group = new PupilGroup(DigestUtils.sha256Hex("guap_group" + groupGUAP.getId()), groupGUAP.getName(), groupGUAP.getId());
            pupilGroupRepository.save(group);
        }

        for(ProffesorEntity professorGUAP : professors.getBody()){
            Professor professor = new Professor(DigestUtils.sha256Hex("guap_professor" + professorGUAP.getId()), professorGUAP.getName(), professorGUAP.getId());
            professorsRepository.save(professor);
        }
        return true;
    }


    public void downloadLessons() {
        List<PupilGroup> pupilGroups = pupilGroupRepository.getAllBy();

        HttpEntity httpEntity = new HttpEntity(new HttpHeaders());
        for (PupilGroup pupilGroup : pupilGroups) {
            ResponseEntity<List<ScheduleGUAPEntity>> lessons = new RestTemplate().exchange("https://api.guap.ru/rasp/custom/get-sem-rasp/group" + pupilGroup.getUniversityGroupId(),
                    HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>() {
                    });


            for (ScheduleGUAPEntity guapLesson : lessons.getBody()) {
                String id = DigestUtils.sha256Hex("guap_lesson" + guapLesson.getItemId());
                String startTime = GUAPUtils.getStartEndTime(guapLesson.getLess()).getFirst();
                String endTime = GUAPUtils.getStartEndTime(guapLesson.getLess()).getSecond();
                int numLesson = guapLesson.getLess();
                String day = GUAPUtils.getDay(guapLesson.getDay());
                String room = guapLesson.getBuild() + ", " + guapLesson.getRooms();
                String type = GUAPUtils.getType(guapLesson.getType());

                Subject subject = subjectRepository.findByName(guapLesson.getDisc()).get(0);
                String week = GUAPUtils.getEven(guapLesson.getWeek());


                int[] groupList = GUAPUtils.prepareList(guapLesson.getGroups());
                Set<PupilGroup> groups = new HashSet<>();
                for (int g : groupList) {
                    groups.add(pupilGroupRepository.findByUniversityGroupId(g));
                }

                int[] professorList = GUAPUtils.prepareList(guapLesson.getPreps());
                Set<Professor> professors = new HashSet<>();
                for (int p : professorList) {
                    professors.add(professorsRepository.findByProfessorUniversityId(p));
                }
                Lesson lesson = new Lesson(id, startTime, endTime, numLesson, day,
                        room, type, subject, professors, groups, week);
                lessonRepository.save(lesson);
            }
        }
    }

    public void updateScheduleInfo() throws JSONException {

        ResponseEntity<String> universityInfo = new RestTemplate().exchange("https://api.guap.ru/rasp/custom/get-sem-info",
                HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<>(){});

        if(!universityInfo.getStatusCode().is2xxSuccessful())
            return;

        JSONObject jsonUniversityInfo = new JSONObject(universityInfo.getBody());
        ScheduleUpdate scheduleUpdate = new ScheduleUpdate();
        String week = jsonUniversityInfo.optBoolean("IsWeekOdd") ? "odd" : "even";

        scheduleUpdate.setName("GUAP");
        scheduleUpdate.setWeek(week);
        scheduleUpdate.setSyncTime(System.currentTimeMillis()/1000);

        scheduleUpdateRepository.save(scheduleUpdate);
    }

}
