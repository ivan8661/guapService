package com.schedguap.schedguap.Services;

import com.schedguap.schedguap.Entities.DatabaseEntities.Lesson;
import com.schedguap.schedguap.Entities.DatabaseEntities.Professor;
import com.schedguap.schedguap.Entities.DatabaseEntities.PupilGroup;
import com.schedguap.schedguap.Entities.DatabaseEntities.Subject;
import com.schedguap.schedguap.Entities.ListAnswer;
import com.schedguap.schedguap.Repositories.LessonRepository;
import com.schedguap.schedguap.Repositories.ProfessorsRepository;
import com.schedguap.schedguap.Repositories.PupilGroupRepository;
import com.schedguap.schedguap.Repositories.SubjectRepository;
import com.schedguap.schedguap.Exceptions.UserException;
import com.schedguap.schedguap.Exceptions.UserExceptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public ListAnswer<Lesson> getLessons(String scheduleUserId) throws UserException {

        Optional<PupilGroup> pupilGroup = pupilGroupRepository.findById(scheduleUserId);
        Optional<Professor> professor = professorsRepository.findById(scheduleUserId);

        if(pupilGroup.isPresent()){
            List<Lesson> lessons = lessonRepository.getAllByGroups(pupilGroup.get());
            return new ListAnswer<>(lessons, lessons.size());
        }
        if(professor.isPresent()){
            List<Lesson> lessons = lessonRepository.getAllByProfessors(professor.get());
            return new ListAnswer<>(lessons, lessons.size());
        }
        throw new UserException(UserExceptionType.OBJECT_NOT_FOUND, "ScheduleUser Doesn't exist!", null);
    }

    public Lesson getLesson(String lessonId) throws UserException {
        Optional<Lesson> lesson = lessonRepository.findById(lessonId);
        if(lesson.isPresent()) {
            return lesson.get();
        } else {
            throw new UserException(UserExceptionType.OBJECT_NOT_FOUND, "Lesson doesn't exist!", null);
        }
    }

    public Subject getSubject(String subjectId) throws UserException {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if(subject.isPresent()) {
            return subject.get();
        } else {
            throw new UserException(UserExceptionType.OBJECT_NOT_FOUND, "Subject doesn't exist!", null);
        }
    }

    public List<Subject> getSubjects(Set<String> subjects) {
        List<Subject> subjectList = new ArrayList<>();
        for(String subjectId : subjects) {
            Optional<Subject> subjectOpt = subjectRepository.findById(subjectId);
            subjectOpt.ifPresent(subjectList::add);
        }
        return subjectList;
    }
}
