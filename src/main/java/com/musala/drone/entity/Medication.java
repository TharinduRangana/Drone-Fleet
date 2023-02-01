package com.musala.drone.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medication {
    public Medication(String name, double weight, String code, byte[] image) {
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.image = image;
    }

    @Id
    @GeneratedValue(generator = "Medication_gen", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "Medication_gen",sequenceName = "Medication_seq", initialValue = 1,allocationSize = 100)
    @Column(name = "id")
    private Integer id;

    @Column(name = "medication_name")
    private String name;

    @Column(name = "medication_weight")
    private double weight;

    @Pattern(regexp = "^[A-Z0-9_]*$", message = "Code should contain only Upper case letters, numbers & underscore ")
    @Column(name = "medication_code")
    private String code;

    @Column(name = "medication_image", unique = false, nullable = false, length = 100000)
    private byte [] image;
}
