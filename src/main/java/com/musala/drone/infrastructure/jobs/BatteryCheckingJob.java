package com.musala.drone.infrastructure.jobs;

import com.musala.drone.entity.Drone;
import com.musala.drone.repository.DroneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class BatteryCheckingJob {

    @Autowired
    private DroneRepository droneRepository;

    @Scheduled(fixedDelay = 43200000)
    public void executeBatteryCheckJob() {
        try {
            String fileName = new SimpleDateFormat("yyyyMMddHHmm'.txt'").format(new Date());
            File myObj = new File("logs/"+fileName);
            myObj.createNewFile();
            FileWriter fileWriter = new FileWriter("logs/"+fileName);

            List<Drone> droneList = droneRepository.findAll();
            droneList.stream().forEach(drone -> {
                try {
                    fileWriter.write("drone details:");
                    fileWriter.write("Drone Id: " + drone.getId() + ", Drone Serial Number: " + drone.getSerialNumber() + ", Battery Level: " + drone.getBatteryCapacity() + "\n");
                } catch (IOException e) {
                    log.error("Error Occured while writing");
                }
                log.info("Drone Id: " + drone.getId() + ", Drone Serial Number: " + drone.getSerialNumber() + ", Battery Level: " + drone.getBatteryCapacity());
            });

            fileWriter.close();
        } catch (IOException e) {
            log.error("An error occurred.");
        }
    }
}
