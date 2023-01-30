package com.musala.drone.domain.request;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class RegisterMedicineRequest {

    private String name;

    private double weight;

    @Pattern(regexp = "^[A-Z0-9_]*$", message = "Code should contain only Upper case letters, numbers & underscore ")
    private String code;

}
