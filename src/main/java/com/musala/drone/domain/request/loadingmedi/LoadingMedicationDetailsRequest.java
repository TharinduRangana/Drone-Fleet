package com.musala.drone.domain.request.loadingmedi;

import com.musala.drone.infrastructure.util.ErrorMessageConstant;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoadingMedicationDetailsRequest {

    @NotNull(message = ErrorMessageConstant.ValidationErrors.MedicationErrorMessages.MEDICATION_ID_CANNOT_BE_NULL)
    private int medicationId;

    @NotNull(message = ErrorMessageConstant.ValidationErrors.MedicationErrorMessages.INVALID_MEDICATION_CODE)
    private String code;

    @Min(value = 0, message = ErrorMessageConstant.ValidationErrors.MedicationErrorMessages.INCORRECT_MEDICATION_WEIGHT)
    private double weight;
}
