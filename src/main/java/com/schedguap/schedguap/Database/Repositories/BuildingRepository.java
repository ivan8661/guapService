package com.schedguap.schedguap.Database.Repositories;


import com.schedguap.schedguap.Database.Entities.Building;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends CrudRepository<Building, Integer> {


}
