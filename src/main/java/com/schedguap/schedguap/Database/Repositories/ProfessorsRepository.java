package com.schedguap.schedguap.Database.Repositories;

import com.schedguap.schedguap.Database.Entities.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorsRepository extends CrudRepository<Professor, String> {

    Professor findByProfessorUniversityId(Integer id);

    Optional<Professor> getById(String id);
}
