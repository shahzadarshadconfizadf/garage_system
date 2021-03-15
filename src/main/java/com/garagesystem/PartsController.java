package com.garagesystem;


import com.garagesystem.model.Parts;
import com.garagesystem.repository.PartsRepository;
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
public class PartsController {
    private static final Logger log = LoggerFactory.getLogger(PartsController.class);

    @Autowired
    PartsRepository partRepository;

    @GetMapping("/parts")
    public ResponseEntity<List<Parts>> getAllParts() {
        try {
            List<Parts> parts = new ArrayList<Parts>();
            partRepository.findAll().forEach(parts::add);

            if (parts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(parts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/parts/{id}")
    public ResponseEntity<Parts> getPartsById(@PathVariable("id") long id) {
        Optional<Parts> partsData = partRepository.findById(id);
        if (partsData.isPresent()) {
            return new ResponseEntity<>(partsData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/parts")
    public ResponseEntity<Parts> createParts(@RequestBody Parts parts) {
        try {
            Parts _parts = partRepository
                    .save(new Parts(parts.getGarageId(), parts.getPartUpdatedBy(), parts.getPartUpdatedDate(), parts.getPartPurchasePrice(), parts.getPartRetailPrice(), parts.getPartName()));
            return new ResponseEntity<>(_parts, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/parts/{id}")
    public ResponseEntity<Parts> updateParts(@PathVariable("id") long id, @RequestBody Parts parts) {
        Optional<Parts> partsData = partRepository.findById(id);
        if (partsData.isPresent()) {
            Parts _parts = partsData.get();
            _parts.setGarageId(parts.getGarageId());
            _parts.setPartUpdatedBy(parts.getPartUpdatedBy());
            _parts.setPartUpdatedDate(parts.getPartUpdatedDate());
            _parts.setPartPurchasePrice(parts.getPartPurchasePrice());
            _parts.setPartRetailPrice(parts.getPartRetailPrice());
            _parts.setPartName(parts.getPartName());

            return new ResponseEntity<>(partRepository.save(_parts), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/parts/{id}")
    public ResponseEntity<HttpStatus> deleteParts(@PathVariable("id") long id) {
        try {
            partRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/parts")
    public ResponseEntity<HttpStatus> deleteAllparts() {
        try {
            partRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}