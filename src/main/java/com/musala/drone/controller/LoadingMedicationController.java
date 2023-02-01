package com.musala.drone.controller;

import com.musala.drone.domain.request.loadingmedi.LoadingMedicationDetailsRequest;
import com.musala.drone.domain.request.loadingmedi.LoadingMedicationRequest;
import com.musala.drone.service.MedicationService;
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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/drone/loading")
@Validated
public class LoadingMedicationController {

    @Autowired
    private MedicationService medicationService;


    @PostMapping("/{droneId}")
    public ResponseEntity<?> loadingDroneWithMedication(@PathVariable int droneId, @Valid @RequestBody List<LoadingMedicationDetailsRequest> request) {
        medicationService.loadingMedicationsToDrone(droneId, request);
        return new ResponseEntity<>("Medication Loaded Successfully",HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, Object> handleValidationExceptions(
            ConstraintViolationException ex) {

        ArrayList<String> errorList = new ArrayList<String>();
        ex.getConstraintViolations().forEach(error-> {
            errorList.add(error.getMessageTemplate());
        });

        Map<String, Object> errors = new HashMap<>();
        errors.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errors.put("error", HttpStatus.BAD_REQUEST.name());
        errors.put("message", errorList);
        return errors;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
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
