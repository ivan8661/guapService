package com.schedguap.schedguap.Entities.Repositories;

import com.schedguap.schedguap.Entities.DatabaseEntities.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorsRepository extends CrudRepository<Professor, String>,
        JpaSpecificationExecutor<Professor> {

    Professor findByProfessorUniversityId(Integer id);

    Optional<Professor> findById(String id);

    List<Professor> getAllBy();

    Page<Professor> findAll(Specification<Professor> spc, Pageable pageable);

    List<Professor> findAll(Specification<Professor> spc);
}
