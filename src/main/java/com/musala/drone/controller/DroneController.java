package com.musala.drone.controller;

import com.musala.drone.domain.request.RegisterDroneRequest;
import com.musala.drone.domain.response.DroneResponse;
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
    public ResponseEntity<DroneResponse> registerDrone(@Valid @RequestBody(required = true) RegisterDroneRequest request) {
        return new ResponseEntity<>(droneService.registerDrone(request), HttpStatus.CREATED);
    }

    @GetMapping("/available")
    public ResponseEntity<?> getAvailableDronesForLoading() {
        return new ResponseEntity<>(droneService.getAvailableDronesForLoading(), HttpStatus.OK);
    }

    @GetMapping("/{droneId}/battery")
    public ResponseEntity<?> getBatteryLevelOfDrone(@PathVariable int droneId) {
        return new ResponseEntity<>(droneService.getBatteryLevelOfDrone(droneId).getBatteryCapacity(), HttpStatus.OK);
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
        ArrayList<String> errorList = new ArrayList<String>();
        ex.getAllErrors().forEach((error)->{
            errorList.add(error.getDefaultMessage());
        });
        Map<String, Object> errors = new HashMap<>();
        errors.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errors.put("error", HttpStatus.BAD_REQUEST.name());
        errors.put("errors", errorList);
        return errors;
    }
}
