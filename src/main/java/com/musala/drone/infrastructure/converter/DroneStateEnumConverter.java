package com.musala.drone.infrastructure.converter;

import com.musala.drone.entity.enums.DroneStateEnum;

import javax.persistence.AttributeConverter;
import java.util.stream.Stream;

public class DroneStateEnumConverter implements AttributeConverter<DroneStateEnum, String> {
    @Override
    public String convertToDatabaseColumn(DroneStateEnum result) {
        if (result == null) {
            return null;
        }
        return result.name();
    }

    @Override
    public DroneStateEnum convertToEntityAttribute(String name) {
        if (name == null) {
            return null;
        }
        return Stream.of(DroneStateEnum.values()).filter(c->c.name().equals(name)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
