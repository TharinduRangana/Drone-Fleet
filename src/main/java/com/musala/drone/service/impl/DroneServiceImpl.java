package com.musala.drone.service.impl;

import com.musala.drone.domain.request.RegisterDroneRequest;
import com.musala.drone.domain.response.RegisterDroneResponse;
import com.musala.drone.entity.Drone;
import com.musala.drone.repository.DroneRepository;
import com.musala.drone.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroneServiceImpl implements DroneService {

    @Autowired
    private DroneRepository droneRepository;
    @Override
    public RegisterDroneResponse registerDrone(RegisterDroneRequest request) {
        Drone drone = createDroneEntityObject(request);
        Drone registeredDrone = droneRepository.save(drone);
        return RegisterDroneResponse.builder()
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
}
