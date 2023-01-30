package com.musala.drone.entity.enums;

import java.util.HashMap;

public enum DroneStateEnum {
    IDLE("IDLE"),
    LOADING("LOADING"),
    LOADED("LOADED"),
    DELIVERING("DELIVERING"),
    DELIVERED("DELIVERED"),
    RETURNING("RETURNING");

    private String displayName;

    private static HashMap<String, DroneStateEnum> enumStringMap = new HashMap<>(DroneStateEnum.values().length);

    static {
        for (DroneStateEnum status: DroneStateEnum.values()) {
            enumStringMap.put(status.displayName, status);
        }
    }

    DroneStateEnum(String displayName) {
        this.displayName = displayName;
    }

    public static DroneStateEnum getInstanceFromString(String name) {
        return enumStringMap.get(name);
    }

    public String getDisplayName() {
        return displayName;
    }

}
