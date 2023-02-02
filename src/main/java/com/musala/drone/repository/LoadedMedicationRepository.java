package com.musala.drone.repository;

import com.musala.drone.entity.LoadedMedication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoadedMedicationRepository extends JpaRepository<LoadedMedication, Integer> {

    List<LoadedMedication> findAllByDrone_IdAndIsDelivered(int droneId, boolean isDelivered);


    @Query(value = "SELECT MEDICATION_ID,DRONE_ID, SUM(MEDICATION_WEIGHT) FROM LOADED_MEDICATION WHERE DRONE_ID=1 AND IS_DELIVERED=false GROUP BY MEDICATION_ID", nativeQuery = true)
    List<LoadedMedication> findLoadedMedications(@Param("id") int id, @Param("delivered") boolean delivered);
}
