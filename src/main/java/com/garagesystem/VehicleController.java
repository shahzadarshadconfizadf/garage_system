package com.garagesystem;


import com.garagesystem.model.Vehicle;
import com.garagesystem.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class VehicleController {
    private static final Logger log = LoggerFactory.getLogger(VehicleController.class);

    @Autowired
    VehicleRepository vehicleRepository;

    @GetMapping("/vehicles")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        try {
            List<Vehicle> vehicles = new ArrayList<Vehicle>();
            vehicleRepository.findAll().forEach(vehicles::add);

            if (vehicles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(vehicles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable("id") long id) {
        Optional<Vehicle> vehicleData = vehicleRepository.findById(id);

        if (vehicleData.isPresent()) {
            return new ResponseEntity<>(vehicleData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/vehicles")
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {
        try {
            Vehicle _vehicle = vehicleRepository
                    .save(new Vehicle(vehicle.getRegistration(), vehicle.getChassisNumber(), vehicle.getVehicleMake(),vehicle.getVehicleModel(), vehicle.getVehicleColour(), vehicle.getTransmission(), vehicle.getFuelType(), vehicle.getManufactureYear(), vehicle.getMileage()));
            return new ResponseEntity<>(_vehicle, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable("id") long id, @RequestBody Vehicle vehicle) {
        Optional<Vehicle> vehicleData = vehicleRepository.findById(id);

        if (vehicleData.isPresent()) {
            Vehicle _vehicle = vehicleData.get();
            _vehicle.setRegistration(vehicle.getRegistration());
            _vehicle.setChassisNumber(vehicle.getChassisNumber());
            _vehicle.setVehicleMake(vehicle.getVehicleMake());
            _vehicle.setVehicleModel(vehicle.getVehicleModel());
            _vehicle.setVehicleColour(vehicle.getVehicleColour());
            _vehicle.setTransmission(vehicle.getTransmission());
            _vehicle.setFuelType(vehicle.getFuelType());
            _vehicle.setManufactureYear(vehicle.getManufactureYear());
            _vehicle.setMileage(vehicle.getMileage());

            return new ResponseEntity<>(vehicleRepository.save(_vehicle), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<HttpStatus> deleteVehicle(@PathVariable("id") long id) {
        try {
            vehicleRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/vehicles")
    public ResponseEntity<HttpStatus> deleteAllVehicles() {
        try {
            vehicleRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}