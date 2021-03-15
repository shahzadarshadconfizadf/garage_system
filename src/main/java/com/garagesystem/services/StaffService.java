package com.garagesystem.services;


import com.garagesystem.model.Staff;
import com.garagesystem.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class StaffService {

    @Autowired
    StaffRepository staffRepository;


    public List<Staff> retrieveAllStaffs(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        List<Staff> staffs = new ArrayList<Staff>();

        if (firstName == null && lastName == null)
            staffRepository.findAll().forEach(staffs::add);
        else
            staffRepository.findByFirstNameAndLastNameContaining(firstName, lastName).forEach(staffs::add);
        return staffs;
    }

    public Staff updateStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    public Optional<Staff> findStaffById(Long id) {
        return staffRepository.findById(id);
    }

    public void deleteStaffById(Long id) {
        staffRepository.deleteById(id);
    }

    public void deleteAllStaffs() {
        staffRepository.deleteAll();
    }

}