package com.musala.drone.repository;

import com.musala.drone.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Integer> {
    @Query(value = "SELECT * FROM MEDICATION WHERE id in :ids", nativeQuery = true)
    public List<Medication> findByIds(@Param("ids") List<Integer> medicationlist);
}
