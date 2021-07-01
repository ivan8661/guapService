package com.schedguap.schedguap.Database.Repositories;


import com.schedguap.schedguap.Database.Entities.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, String> {

    List<Subject> findByName(String name);

    Subject findBySubjectUniversityId(Integer id);
}
