package com.schedguap.schedguap.Entities.Repositories;


import com.schedguap.schedguap.Entities.DatabaseEntities.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, String> {



}
