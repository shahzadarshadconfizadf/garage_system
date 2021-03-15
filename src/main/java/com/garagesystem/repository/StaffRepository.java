package com.garagesystem.repository;
import java.util.List;

import com.garagesystem.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    List<Staff> findByFirstNameAndLastNameContaining(String firstName,String lastName);
    public Staff findByUserName(String userName);
}
