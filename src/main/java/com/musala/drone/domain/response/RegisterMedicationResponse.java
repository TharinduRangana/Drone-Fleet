package com.musala.drone.domain.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterMedicationResponse {

    private Integer id;

    private String name;

    private double weight;

    private String code;

    private byte [] image;
}
