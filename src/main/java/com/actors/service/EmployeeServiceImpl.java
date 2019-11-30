package com.actors.service;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import com.actors.actor.EmployeeActor;
import com.actors.util.CSVUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;

import static com.actors.config.SpringExtension.SPRING_EXTENSION_PROVIDER;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private ActorSystem system;

    @Override
    public void uploadEmployees(String fileURL) throws Exception {
        ActorRef employeeActor = system.actorOf(SPRING_EXTENSION_PROVIDER.get(system).props("employeeActor"), "employee");

        List<EmployeeActor.Employee> employees = CSVUtils.read(EmployeeActor.Employee.class, new URL(fileURL).openStream());
        employees.stream()
                .forEach(employee -> employeeActor.tell(employee,ActorRef.noSender()));

        employeeActor.tell(PoisonPill.getInstance(),ActorRef.noSender());

    }
}
