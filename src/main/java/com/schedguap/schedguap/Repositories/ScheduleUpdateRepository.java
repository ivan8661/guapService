package com.schedguap.schedguap.Repositories;


import com.schedguap.schedguap.Entities.DatabaseEntities.ScheduleUpdate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleUpdateRepository extends CrudRepository<ScheduleUpdate, String> {


    @Override
    void deleteAll();

    public ScheduleUpdate findByName(String name);

}
