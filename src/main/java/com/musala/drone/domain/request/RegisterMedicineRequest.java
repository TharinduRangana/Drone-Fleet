package com.musala.drone.domain.request;

import com.musala.drone.infrastructure.util.ErrorMessageConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterMedicineRequest {

    @NotNull(message = ErrorMessageConstant.ValidationErrors.MedicationErrorMessages.MEDICATION_NAME_CANNOT_BE_NULL)
    private String name;

    @Min(value = 0, message = ErrorMessageConstant.ValidationErrors.MedicationErrorMessages.INCORRECT_MEDICATION_WEIGHT)
    private double weight;

    @Pattern(regexp = "^[A-Z0-9_]*$", message = ErrorMessageConstant.ValidationErrors.MedicationErrorMessages.INVALID_MEDICATION_CODE)
    private String code;

}
