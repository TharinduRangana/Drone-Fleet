package com.musala.drone.infrastructure.converter;

import com.musala.drone.entity.enums.DroneModelEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter
public class DroneModelEnumConverter implements AttributeConverter<DroneModelEnum, String> {
    @Override
    public String convertToDatabaseColumn(DroneModelEnum result) {
        if (result == null) {
            return null;
        }
        return result.name();
    }

    @Override
    public DroneModelEnum convertToEntityAttribute(String name) {
        if (name == null) {
            return null;
        }
        return Stream.of(DroneModelEnum.values()).filter(c->c.name().equals(name)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
