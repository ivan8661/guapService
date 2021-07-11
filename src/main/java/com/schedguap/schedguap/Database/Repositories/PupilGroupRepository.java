package com.schedguap.schedguap.Database.Repositories;

import com.schedguap.schedguap.Database.Entities.PupilGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PupilGroupRepository extends CrudRepository<PupilGroup, String> {


    List<PupilGroup> getAllBy();

    PupilGroup findByUniversityGroupId(Integer id);

    Optional<PupilGroup> getById(String id);



}
