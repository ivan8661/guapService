package com.schedguap.schedguap.Repositories;


import com.schedguap.schedguap.Entities.DatabaseEntities.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, String> {

    List<Subject> findByName(String name);

    @Override
    void deleteAll();
    Optional<Subject> findById(String id);
}
