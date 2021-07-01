package com.schedguap.schedguap.Database.Repositories;

import com.schedguap.schedguap.Database.Entities.PupilGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PupilGroupRepository extends CrudRepository<PupilGroup, String> {


    public List<PupilGroup> getAllBy();

    public PupilGroup findByUniversityGroupId(Integer id);
}
