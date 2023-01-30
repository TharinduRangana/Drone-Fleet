package com.musala.drone.domain.request;

import com.musala.drone.entity.enums.DroneModelEnum;
import com.musala.drone.entity.enums.DroneStateEnum;
import com.musala.drone.infrastructure.util.ErrorMessageConstant;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RegisterDroneRequest {

    @Size(min=1, max = 100, message = ErrorMessageConstant.ValidationErrors.DroneErrorMessages.INCORRECT_SERIAL_NUMBER)
    private String serialNumber;

    @NotNull(message = ErrorMessageConstant.ValidationErrors.DroneErrorMessages.DRONE_MODEL_CANNOT_BE_NULL)
    private DroneModelEnum model;

    @Min(value=0,message = ErrorMessageConstant.ValidationErrors.DroneErrorMessages.WEIGHT_LIMIT_CANNOT_BE_MINUS)
    @Max(value=500,message = ErrorMessageConstant.ValidationErrors.DroneErrorMessages.WEIGHT_LIMIT_EXCEEDED)
    private int weightLimit;

    @Min(value = 0, message = ErrorMessageConstant.ValidationErrors.DroneErrorMessages.INVALID_BATTERY_CAPACITY)
    @Max(value = 100L, message = ErrorMessageConstant.ValidationErrors.DroneErrorMessages.INVALID_BATTERY_CAPACITY)
    @Digits(integer=3, fraction=2, message = ErrorMessageConstant.ValidationErrors.DroneErrorMessages.INVALID_BATTERY_CAPACITY)
    private double batteryCapacity;

    @NotNull(message = ErrorMessageConstant.ValidationErrors.DroneErrorMessages.DRONE_STATE_CANNOT_BE_NULL)
    private DroneStateEnum state;
}
