package com.musala.drone.entity.enums;

import java.util.HashMap;

public enum DroneModelEnum {
    LIGHTWEIGHT("LIGHTWEIGHT"),
    MIDDLEWEIGHT("MIDDLEWEIGHT"),
    CRUISERWEIGHT("CRUISERWEIGHT"),
    HEAVYWEIGHT("HEAVYWEIGHT");

    private String displayName;

    private static HashMap<String, DroneModelEnum> enumStringMap = new HashMap<>(DroneModelEnum.values().length);

    static {
        for (DroneModelEnum model: DroneModelEnum.values()) {
            enumStringMap.put(model.displayName, model);
        }
    }

    DroneModelEnum(String displayName) {
        this.displayName = displayName;
    }

    public static DroneModelEnum getInstanceFromString(String name) {
        return enumStringMap.get(name);
    }

    public String getDisplayName() {
        return displayName;
    }
    

}
