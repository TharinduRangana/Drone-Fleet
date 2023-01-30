package com.musala.drone.controller;

import com.musala.drone.domain.request.RegisterDroneRequest;
import com.musala.drone.domain.response.RegisterDroneResponse;
import com.musala.drone.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/drone")
@Validated
public class DroneController {

    @Autowired
    private DroneService droneService;

    @PostMapping("/register")
    public ResponseEntity<RegisterDroneResponse> registerDrone(@Valid @RequestBody(required = true) RegisterDroneRequest request) {
        return new ResponseEntity<>(droneService.registerDrone(request), HttpStatus.CREATED);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleValidationExceptions(
            ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errors.put("error", HttpStatus.BAD_REQUEST.name());
        errors.put("message", ex.getMessage());
        return errors;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
//        System.out.println(ex.get);
        ArrayList<String> errorList = new ArrayList<String>();
        ex.getAllErrors().forEach((error)->{
            System.out.println(error);
            errorList.add(error.getDefaultMessage());
        });
        Map<String, Object> errors = new HashMap<>();
        errors.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errors.put("error", HttpStatus.BAD_REQUEST.name());
        errors.put("errors", errorList);
        return errors;
    }
}
