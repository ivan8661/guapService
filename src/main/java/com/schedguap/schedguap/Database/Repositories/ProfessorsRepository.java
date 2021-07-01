package com.schedguap.schedguap.Database.Repositories;

import com.schedguap.schedguap.Database.Entities.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorsRepository extends CrudRepository<Professor, String> {

    Professor findByProfessorUniversityId(Integer id);
}
