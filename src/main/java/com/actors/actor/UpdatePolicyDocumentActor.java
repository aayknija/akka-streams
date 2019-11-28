package com.actors.actor;

import akka.actor.AbstractActor;

public class UpdatePolicyDocumentActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder().build();
    }
}
