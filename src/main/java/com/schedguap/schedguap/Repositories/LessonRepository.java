package com.schedguap.schedguap.Repositories;


import com.schedguap.schedguap.Entities.DatabaseEntities.Lesson;
import com.schedguap.schedguap.Entities.DatabaseEntities.Professor;
import com.schedguap.schedguap.Entities.DatabaseEntities.PupilGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, String> {


    List<Lesson> getAllBy();


    List<Lesson> getAllByGroups(PupilGroup pupilGroup);

    List<Lesson> getAllByProfessors(Professor professor);

}
