package com.schedguap.schedguap.Services;

import com.schedguap.schedguap.Entities.DatabaseEntities.Lesson;
import com.schedguap.schedguap.Entities.DatabaseEntities.Professor;
import com.schedguap.schedguap.Entities.DatabaseEntities.PupilGroup;
import com.schedguap.schedguap.Entities.Repositories.LessonRepository;
import com.schedguap.schedguap.Entities.Repositories.ProfessorsRepository;
import com.schedguap.schedguap.Entities.Repositories.PupilGroupRepository;
import com.schedguap.schedguap.Exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    private LessonRepository lessonRepository;

    private PupilGroupRepository pupilGroupRepository;

    private ProfessorsRepository professorsRepository;

    @Autowired
    public ScheduleService(LessonRepository lessonRepository, PupilGroupRepository pupilGroupRepository,
                           ProfessorsRepository professorsRepository) {
        this.lessonRepository = lessonRepository;
        this.pupilGroupRepository = pupilGroupRepository;
        this.professorsRepository = professorsRepository;
    }






    public List<Lesson> getLessons(String scheduleUserId) throws UserException {

        Optional<PupilGroup> pupilGroup = pupilGroupRepository.findById(scheduleUserId);
        Optional<Professor> professor = professorsRepository.findById(scheduleUserId);

        if(pupilGroup.isPresent()){
            return lessonRepository.getAllByGroups(pupilGroup.get());
        }
        if(professor.isPresent()){
            return lessonRepository.getAllByProfessors(professor.get());
        }
        throw new UserException(404, "404", "ScheduleUser Doesn't exist!", "");
    }
}
