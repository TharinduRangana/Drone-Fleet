package com.musala.drone.service.impl;

import com.musala.drone.domain.request.RegisterDroneRequest;
import com.musala.drone.domain.response.DroneResponse;
import com.musala.drone.entity.Drone;
import com.musala.drone.entity.enums.DroneStateEnum;
import com.musala.drone.infrastructure.exceptions.DroneNotAvailableException;
import com.musala.drone.repository.DroneRepository;
import com.musala.drone.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class DroneServiceImpl implements DroneService {

    @Autowired
    private DroneRepository droneRepository;
    @Override
    public DroneResponse registerDrone(RegisterDroneRequest request) {
        Drone drone = createDroneEntityObject(request);
        Drone registeredDrone = droneRepository.save(drone);
        return DroneResponse.builder()
                .id(registeredDrone.getId())
                .model(registeredDrone.getModel())
                .state(registeredDrone.getState())
                .batteryCapacity(registeredDrone.getBatteryCapacity())
                .serialNumber(registeredDrone.getSerialNumber())
                .weightLimit(registeredDrone.getWeightLimit())
                .build();
    }

    private Drone createDroneEntityObject(RegisterDroneRequest request) {
        Drone drone = new Drone();
        drone.setSerialNumber(request.getSerialNumber());
        drone.setModel(request.getModel());
        drone.setWeightLimit(request.getWeightLimit());
        drone.setBatteryCapacity(request.getBatteryCapacity());
        drone.setState(request.getState());
        return drone;
    }

    public List<DroneResponse> getAvailableDronesForLoading() {
        List<Drone> availableDrones = droneRepository.findAllByBatteryCapacityGreaterThanAndStateIn(25.0, Arrays.asList(DroneStateEnum.IDLE,DroneStateEnum.LOADING));
        List<DroneResponse> response = new ArrayList<>();
        availableDrones.stream().forEach(drone -> {
            DroneResponse droneResponse = DroneResponse.builder()
                            .id(drone.getId())
                            .model(drone.getModel())
                            .state(drone.getState())
                            .batteryCapacity(drone.getBatteryCapacity())
                            .serialNumber(drone.getSerialNumber())
                            .weightLimit(drone.getWeightLimit())
                            .build();
            response.add(droneResponse);
        });
        return response;
    }

    public DroneResponse getBatteryLevelOfDrone(int droneId) {
        Optional<Drone> optionalSelectedDrone = droneRepository.findById(droneId);
        if (optionalSelectedDrone.isPresent()) {
            Drone drone = optionalSelectedDrone.get();
            return DroneResponse.builder()
                    .id(drone.getId())
                    .model(drone.getModel())
                    .state(drone.getState())
                    .batteryCapacity(drone.getBatteryCapacity())
                    .serialNumber(drone.getSerialNumber())
                    .weightLimit(drone.getWeightLimit())
                    .build();
        } else {
            throw new DroneNotAvailableException("Requested Drone Is Not Available At This Moment!");
        }
    }
}
