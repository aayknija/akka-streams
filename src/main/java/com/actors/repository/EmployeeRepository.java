package com.actors.repository;

import com.actors.actor.EmployeeActor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<EmployeeActor.Employee,String> {
}
