package com.musala.drone.infrastructure.util;

public interface ErrorMessageConstant {
    interface ValidationErrors {
        interface DroneErrorMessages {
            String INCORRECT_SERIAL_NUMBER = "Incorrect Serial Number!";

            String WEIGHT_LIMIT_EXCEEDED = "Weight Limit Exceeded!";

            String WEIGHT_LIMIT_CANNOT_BE_MINUS = "Weight Limit Cannot Be Minus Value!";

            String INVALID_BATTERY_CAPACITY="Invalid Battery Capacity!";

            String DRONE_STATE_CANNOT_BE_NULL = "Drone State Cannot Be Null!";

            String DRONE_MODEL_CANNOT_BE_NULL = "Drone Model Cannot Be Null!";

        }

        interface MedicationErrorMessages {

            String MEDICATION_NAME_CANNOT_BE_NULL ="Medication Name Cannot Be Null!";

            String INCORRECT_MEDICATION_WEIGHT ="Incorrect Medication Weight!";

            String INVALID_MEDICATION_CODE = "Invalid Medication Code!";

            String MEDICATION_ID_CANNOT_BE_NULL = "Medication ID Cannot Be Null!";
        }
    }
}
