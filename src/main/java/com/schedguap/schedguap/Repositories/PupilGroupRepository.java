package com.schedguap.schedguap.Repositories;

import com.schedguap.schedguap.Entities.DatabaseEntities.PupilGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PupilGroupRepository extends CrudRepository<PupilGroup, String> {


    List<PupilGroup> getAllBy();

    @Override
    void deleteAll();

    PupilGroup findByUniversityGroupId(Integer id);

    Optional<PupilGroup> getById(String id);

    Optional<PupilGroup> findPupilGroupByName(String name);

    Page<PupilGroup> findAll(Specification<PupilGroup> spc, Pageable pageable);

    List<PupilGroup> findAll(Specification<PupilGroup> spc);
}
