package com.musala.drone.controller;

import com.musala.drone.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/medicine")
@Validated
public class MedicineController {

    @Autowired
    private MedicationService medicationService;

    @PostMapping(value = "/add/medicine")
    public ResponseEntity<?> addNewMedicine(@RequestParam("medicine") String medicine, @RequestParam("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(medicationService.addNewMedicine(medicine, file), HttpStatus.CREATED);
    }
}
