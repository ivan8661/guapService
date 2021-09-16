package com.schedguap.schedguap.Entities.Repositories;


import com.schedguap.schedguap.Entities.DatabaseEntities.ScheduleUpdate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleUpdateRepository extends CrudRepository<ScheduleUpdate, String> {


    public ScheduleUpdate findByName(String name);

}
