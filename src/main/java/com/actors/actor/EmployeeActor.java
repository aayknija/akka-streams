package com.actors.actor;

import akka.actor.AbstractActor;
import com.actors.repository.EmployeeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EmployeeActor extends AbstractActor {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Data
    public static final class Employee {
        @Id
        private String id;
        private String firstName;
        private String lastName;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Employee.class, employee -> addEmployee(employee))
                .build();
    }
    private Employee addEmployee(Employee employee){
        employeeRepository.save(employee);
        return employee;
    }
}
