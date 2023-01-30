package com.musala.drone.controller;

import com.musala.drone.infrastructure.util.ErrorMessageConstant;
import com.musala.drone.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/medicine")
@Validated
public class MedicineController {

    @Autowired
    private MedicationService medicationService;

    @PostMapping(value = "/add/medicine")
    public ResponseEntity<?> addNewMedicine(
            @Valid @RequestParam("name") @NotNull(message = ErrorMessageConstant.ValidationErrors.MedicationErrorMessages.MEDICATION_NAME_CANNOT_BE_NULL) String name,
            @Valid @RequestParam("weight") @Min(value = 0, message = ErrorMessageConstant.ValidationErrors.MedicationErrorMessages.INCORRECT_MEDICATION_WEIGHT) Double weight,
            @Valid @RequestParam("code") @Pattern(regexp = "^[A-Z0-9_]*$", message = ErrorMessageConstant.ValidationErrors.MedicationErrorMessages.INVALID_MEDICATION_CODE) String code,
            @RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(medicationService.addNewMedicine(name, weight, code, file), HttpStatus.CREATED);
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, Object> handleValidationExceptions(
            ConstraintViolationException ex) {
        ArrayList<String> errorList = new ArrayList<String>();
        ex.getConstraintViolations().forEach(x->{
            errorList.add(x.getMessage());
        });
        Map<String, Object> errors = new HashMap<>();
        errors.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errors.put("error", HttpStatus.BAD_REQUEST.name());
        errors.put("errorsList", errorList);
        return errors;
    }
}
