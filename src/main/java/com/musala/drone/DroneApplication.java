package com.musala.drone;

import com.musala.drone.entity.Drone;
import com.musala.drone.entity.Medication;
import com.musala.drone.entity.enums.DroneModelEnum;
import com.musala.drone.entity.enums.DroneStateEnum;
import com.musala.drone.infrastructure.util.ImageUtility;
import com.musala.drone.repository.DroneRepository;
import com.musala.drone.repository.MedicationRepository;
import com.musala.drone.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@EnableSwagger2
@SpringBootApplication
public class DroneApplication implements CommandLineRunner {

	@Autowired
	private MedicationRepository medicationRepository;

	@Autowired
	private DroneRepository droneRepository;

	public static void main(String[] args) {
		SpringApplication.run(DroneApplication.class, args);
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.musala.drone")).build();
	}

	@Override
	public void run(String... args) throws Exception {
		addMedicationsWhenStarting();
		addDronesWhenStarting();
	}

	private void addDronesWhenStarting() {
		Drone droneA = new Drone("Yw9npGR1rc", DroneModelEnum.LIGHTWEIGHT, 350, 85, DroneStateEnum.IDLE, 0.0);
		Drone droneB = new Drone("JdfIVdadTq", DroneModelEnum.MIDDLEWEIGHT, 400, 70, DroneStateEnum.LOADING, 300.0);
		Drone droneC = new Drone("Gmkzte1op1", DroneModelEnum.CRUISERWEIGHT, 450, 70, DroneStateEnum.DELIVERING, 250.0);
		Drone droneD = new Drone("kdJqZQk0wd", DroneModelEnum.HEAVYWEIGHT, 500, 100, DroneStateEnum.IDLE, 0.0);

		droneRepository.saveAll(Arrays.asList(droneA, droneB, droneC, droneD));
	}

	private void addMedicationsWhenStarting() throws IOException {
		File file = ResourceUtils.getFile("classpath:medi.jpg");
		FileInputStream fl = new FileInputStream(file);
		byte[] arr = new byte[(int)file.length()];
		fl.read(arr);
		fl.close();
		Medication medicA = new Medication("Medi A", 400.0, "MEDIC_A", ImageUtility.compressImage(arr));
		Medication medicB = new Medication("Medi B", 250.50, "MEDIC_B", ImageUtility.compressImage(arr));
		Medication medicC = new Medication("Medi C", 380.90, "MEDIC_C", ImageUtility.compressImage(arr));
		medicationRepository.saveAll(Arrays.asList(medicA, medicB, medicC));
	}
}
