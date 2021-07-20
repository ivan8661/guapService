package com.schedguap.schedguap.Services;

import com.schedguap.schedguap.Entities.DatabaseEntities.Lesson;
import com.schedguap.schedguap.Entities.DatabaseEntities.Professor;
import com.schedguap.schedguap.Entities.DatabaseEntities.PupilGroup;
import com.schedguap.schedguap.Entities.DatabaseEntities.Subject;
import com.schedguap.schedguap.Entities.Repositories.LessonRepository;
import com.schedguap.schedguap.Entities.Repositories.ProfessorsRepository;
import com.schedguap.schedguap.Entities.Repositories.PupilGroupRepository;
import com.schedguap.schedguap.Entities.Repositories.SubjectRepository;
import com.schedguap.schedguap.Exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    private final LessonRepository lessonRepository;

    private final PupilGroupRepository pupilGroupRepository;

    private final ProfessorsRepository professorsRepository;

    private final SubjectRepository subjectRepository;

    @Autowired
    public ScheduleService(LessonRepository lessonRepository, PupilGroupRepository pupilGroupRepository,
                           ProfessorsRepository professorsRepository, SubjectRepository subjectRepository) {
        this.lessonRepository = lessonRepository;
        this.pupilGroupRepository = pupilGroupRepository;
        this.professorsRepository = professorsRepository;
        this.subjectRepository = subjectRepository;
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

    public Lesson getLesson(String lessonId) throws UserException {
        Optional<Lesson> lesson = lessonRepository.findById(lessonId);
        if(lesson.isPresent()) {
            return lesson.get();
        } else {
            throw new UserException(404, "404", "Lesson doesn't exist!", " ");
        }
    }

    public Subject getSubject(String subjectId) throws UserException {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if(subject.isPresent()) {
            return subject.get();
        } else {
            throw new UserException(404, "404", "Subject doesn't exist!", " ");
        }
    }
}
