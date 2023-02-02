package com.musala.drone.service;

import com.musala.drone.domain.request.RegisterDroneRequest;
import com.musala.drone.domain.response.DroneResponse;

public interface DroneService {
    public DroneResponse registerDrone(RegisterDroneRequest request);
}
