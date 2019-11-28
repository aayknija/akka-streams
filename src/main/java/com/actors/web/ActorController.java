package com.actors.web;

import com.actors.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActorController {

    @Autowired
    private ActorService actorService;

    @PostMapping
    public void uploadFiles(@RequestParam("fileURL") String fileURL){
        actorService.uploadFile(fileURL);
    }
}
