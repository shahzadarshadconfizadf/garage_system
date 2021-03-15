package com.garagesystem.services;


import com.garagesystem.dto.StaffMemberDetails;
import com.garagesystem.model.Staff;
import com.garagesystem.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class StaffMemberDetailsService implements UserDetailsService {

    @Autowired
    private StaffRepository staffRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Staff staff = staffRepo.findByUserName(username);
        if (staff == null) {
            throw new UsernameNotFoundException("Staff Member not found");
        }
        return new StaffMemberDetails(staff);
    }

}