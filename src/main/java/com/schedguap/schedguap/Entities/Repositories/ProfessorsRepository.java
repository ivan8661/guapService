package com.schedguap.schedguap.Entities.Repositories;

import com.schedguap.schedguap.Entities.DatabaseEntities.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorsRepository extends CrudRepository<Professor, String> {

    Professor findByProfessorUniversityId(Integer id);

    Optional<Professor> findById(String id);

    List<Professor> getAllBy();
}
