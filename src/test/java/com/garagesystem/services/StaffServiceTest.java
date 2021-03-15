package com.garagesystem.services;


import com.garagesystem.model.Staff;
import com.garagesystem.repository.StaffRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class StaffServiceTest {

    @Mock
    private StaffRepository userRepository;

    @InjectMocks
    private StaffService userService;

    @Test
    void shouldSavedUserSuccessFully() {


        final Staff _staffObj = new Staff("Shahzad", "Arshad", "587954ABZ", "Test Comments", "shahzad.arshad@gmail4.com", "Confiz123", "admin");
        _staffObj.setId(56);

        given(userRepository.save(_staffObj)).willReturn(_staffObj);
        Staff savedUser = userService.createStaff(_staffObj);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser);
        verify(userRepository).save(any(Staff.class));

    }

    @Test
    void updateUser() {
        final Staff _staffObj = new Staff("Shahzad", "Arshad", "587954ABZ", "Test Comments", "shahzad.arshad@gmail.com", "Confiz123", "admin");

        given(userRepository.save(_staffObj)).willReturn(_staffObj);
        final Staff expected = userService.updateStaff(_staffObj);
        assertThat(expected).isNotNull();
        verify(userRepository).save(any(Staff.class));
    }


    @Test
    void findStaffById() {
        final Long id = 10L;
        final Staff _staffObj = new Staff("Shahzad", "Arshad", "587954ABZ", "Test Comments", "shahzad.arshad@gmail.com", "Confiz123", "admin");


        given(userRepository.findById(id)).willReturn(Optional.of(_staffObj));

        final Optional<Staff> expected = userService.findStaffById(id);

        assertThat(expected).isNotNull();

    }

    @Test
    void shouldBeDelete() {
        final Long userId = 10L;

        userService.deleteStaffById(userId);
        userService.deleteStaffById(userId);

        verify(userRepository, times(2)).deleteById(userId);
    }

    @Test
    void shouldBeDeleteAll() {

        userService.deleteAllStaffs();
        verify(userRepository, times(1)).deleteAll();
    }



    @Test
    void shouldReturnFindAll() {
        List<Staff> datas = new ArrayList();
        datas.add(new Staff("Shahzad", "Arshad", "587954ABZ", "Test Comments", "shahzad.arshad@gmail.com", "Confiz123", "admin"));
        datas.add(new Staff("Shahzad", "Arshad", "587954ABZ", "Test Comments", "shahzad.arshad@gmail.com", "Confiz123", "admin"));
        datas.add(new Staff("Shahzad", "Arshad", "587954ABZ", "Test Comments", "shahzad.arshad@gmail.com", "Confiz123", "admin"));

        given(userRepository.findAll()).willReturn(datas);

        List<Staff> expected = userService.retrieveAllStaffs(null, null);
        List<Staff> expected1 = userService.retrieveAllStaffs("Shahzad", "Arshad");

        assertEquals(expected, datas);
        assertThat(expected1).isNotNull();
    }

}