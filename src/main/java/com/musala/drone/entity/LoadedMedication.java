package com.musala.drone.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class LoadedMedication {


    @Id
    @GeneratedValue(generator = "loaded_medi_gen", strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "loaded_medi_gen",sequenceName = "loaded_medi_seq", initialValue = 1,allocationSize = 100)
    @Column(name = "id")
    private int id;


//    @Column(name = "drone_id")
    @ManyToOne
    @JoinColumn(name = "drone_id")
    private Drone drone;

//    @Column(name = "medication_id")
    @ManyToOne
    @JoinColumn(name = "medication_id")
    private Medication medication;

    @Column(name = "medication_weight")
    private double medicationWeight;

    @Column(name = "is_delivered")
    private boolean isDelivered;
}
