package com.schedguap.schedguap.Repositories;


import com.schedguap.schedguap.Entities.DatabaseEntities.Building;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends CrudRepository<Building, Integer> {

        void deleteAll();
}
