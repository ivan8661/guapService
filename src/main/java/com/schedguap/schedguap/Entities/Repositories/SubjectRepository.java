package com.schedguap.schedguap.Entities.Repositories;


import com.schedguap.schedguap.Entities.DatabaseEntities.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, String> {

    List<Subject> findByName(String name);

    Subject findBySubjectUniversityId(Integer id);
}
