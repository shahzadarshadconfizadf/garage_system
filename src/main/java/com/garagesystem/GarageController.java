package com.garagesystem;


import com.garagesystem.model.Garage;
import com.garagesystem.repository.GarageRepository;
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
public class GarageController {
    private static final Logger log = LoggerFactory.getLogger(GarageController.class);

    @Autowired
    GarageRepository garageRepository;

    @GetMapping("/garages")
    public ResponseEntity<List<Garage>> getAllGarages() {
        try {
            List<Garage> garages = new ArrayList<Garage>();
            garageRepository.findAll().forEach(garages::add);

            if (garages.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(garages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/garages/{id}")
    public ResponseEntity<Garage> getGarageById(@PathVariable("id") long id) {
        Optional<Garage> garageData = garageRepository.findById(id);
        if (garageData.isPresent()) {
            return new ResponseEntity<>(garageData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/garages")
    public ResponseEntity<Garage> createGarage(@RequestBody Garage garage) {
        try {
            Garage _garage = garageRepository
                    .save(new Garage(garage.getAdministratorId(), garage.getAddressLine1(), garage.getAddressLine2(), garage.getCity(), garage.getCounty(), garage.getPostcode(), garage.getTelephone(), garage.getRegisteredVat(), garage.getName()));
            return new ResponseEntity<>(_garage, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/garages/{id}")
    public ResponseEntity<Garage> updateGarage(@PathVariable("id") long id, @RequestBody Garage garage) {
        Optional<Garage> garageData = garageRepository.findById(id);

        if (garageData.isPresent()) {
            Garage _garage = garageData.get();
            _garage.setAdministratorId(garage.getAdministratorId());
            _garage.setAddressLine1(garage.getAddressLine1());
            _garage.setAddressLine2(garage.getAddressLine2());
            _garage.setCity(garage.getCity());
            _garage.setCounty(garage.getCounty());
            _garage.setPostcode(garage.getPostcode());
            _garage.setTelephone(garage.getTelephone());
            _garage.setRegisteredVat(garage.getRegisteredVat());
            _garage.setName(garage.getName());

            return new ResponseEntity<>(garageRepository.save(_garage), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/garages/{id}")
    public ResponseEntity<HttpStatus> deleteGarage(@PathVariable("id") long id) {
        try {
            garageRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/garages")
    public ResponseEntity<HttpStatus> deleteAllGarages() {
        try {
            garageRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}