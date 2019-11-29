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
public class Employee extends AbstractActor {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Data
   public static final class EmployeeRequest {
        @Id
        private String id;
        private String firstName;
        private String lastName;
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(EmployeeRequest.class,employeeRequest -> addEmployee(employeeRequest))
                .build();
    }
    private EmployeeRequest addEmployee(EmployeeRequest employeeRequest){
        employeeRepository.save(employeeRequest);
        return employeeRequest;
    }
}
