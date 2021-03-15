package com.garagesystem;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.garagesystem.model.Staff;
import com.garagesystem.repository.StaffRepository;
import com.garagesystem.services.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class StaffController {
    private static final Logger log = LoggerFactory.getLogger(StaffController.class);

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private StaffService staffService;

    @GetMapping("/staffs")
    public ResponseEntity<List<Staff>> getAllStaffs(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        try {
            List<Staff> staffs = new ArrayList<Staff>();
            staffs = staffService.retrieveAllStaffs(firstName, lastName);

            if (staffs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(staffs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/staffs/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable("id") long id) {
        Optional<Staff> staffData = staffRepository.findById(id);

        if (staffData.isPresent()) {
            return new ResponseEntity<>(staffData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/staffs")
    public ResponseEntity<Staff> createStaff(@RequestBody Staff staff) {
        try {

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(staff.getPassword());
            staff.setPassword(encodedPassword);

            Staff _staffObj = new Staff(staff.getFirstName(), staff.getLastName(), staff.getExtNumber(), staff.getComments(), staff.getUserName(), staff.getPassword(), staff.getRole());

            Staff _staff = staffService.createStaff(_staffObj);

            return new ResponseEntity<>(_staff, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/staffs/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable("id") long id, @RequestBody Staff staff) {
        Optional<Staff> staffData = staffRepository.findById(id);

        if (staffData.isPresent()) {
            Staff _staffObj = staffData.get();
            _staffObj.setFirstName(staff.getFirstName());
            _staffObj.setLastName(staff.getLastName());
            _staffObj.setExtNumber(staff.getExtNumber());
            _staffObj.setComments(staff.getComments());
            _staffObj.setUserName(staff.getUserName());
            _staffObj.setPassword(staff.getPassword());
            _staffObj.setRole(staff.getRole());

            Staff _staff = staffService.updateStaff(_staffObj);

            return new ResponseEntity<>(_staff, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/staffs/{id}")
    public ResponseEntity<HttpStatus> deleteStaff(@PathVariable("id") long id) {
        try {
            staffService.deleteStaffById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/staffs")
    public ResponseEntity<HttpStatus> deleteAllStaffs() {
        try {
            staffService.deleteAllStaffs();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}