package com.musala.drone.repository;

import com.musala.drone.entity.LoadedMedication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoadedMedicationRepository extends JpaRepository<LoadedMedication, Integer> {

}
