package com.musala.drone.controller;

import com.musala.drone.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private DroneService droneService;

    @PostMapping("/check")
    public ResponseEntity<?> checkMethod(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
