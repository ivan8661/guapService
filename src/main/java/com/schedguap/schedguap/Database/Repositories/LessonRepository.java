package com.schedguap.schedguap.Database.Repositories;


import com.schedguap.schedguap.Database.Entities.Lesson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, String> {



}
