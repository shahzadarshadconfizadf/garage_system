package com.garagesystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.garagesystem.MainApplicationClass;
import com.garagesystem.StaffController;
import com.garagesystem.model.Staff;
import com.garagesystem.repository.StaffRepository;
import com.garagesystem.services.StaffService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.validation.ConstraintViolationProblemModule;
//import org.zalando.problem.violations.ConstraintViolationProblemModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/***
 * Project Name     : spring-boot-testing
 * Username         : Teten Nugraha
 * Date Time        : 12/18/2019
 * Telegram         : @tennugraha
 */




@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplicationClass.class)
@AutoConfigureMockMvc

class StaffControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StaffRepository userRepository;

    @Autowired
    private StaffService staffService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Staff> userList;

    @BeforeEach
    void setUp() {
        this.userList = new ArrayList<Staff>();
        this.userList.add(new Staff("Shahzad", "Arshad", "587954ABZ", "Test Comments", "shahzad.arshad@gmail.com", "Confiz123", "admin"));
        this.userList.add(new Staff("Shahzad", "Arshad", "587954ABZ", "Test Comments", "shahzad.arshad@gmail.com", "Confiz123", "admin"));
        this.userList.add(new Staff("Shahzad", "Arshad", "587954ABZ", "Test Comments", "shahzad.arshad@gmail.com", "Confiz123", "admin"));

        objectMapper.registerModule(new ProblemModule());
        objectMapper.registerModule(new ConstraintViolationProblemModule());
    }

    @WithMockUser(value = "spring")
    @Test
    void shouldFetchAllUsers() throws Exception {

        given(userRepository.findAll()).willReturn(userList);


        this.mockMvc.perform(get("/api/staffs"))
                .andExpect(status().isOk());
    }

    @WithMockUser(value = "spring")
    @Test
    void shouldFetchOneUserById() throws Exception {
        final Long userId = 1L;
        final Staff user = new Staff("Shahzad", "Arshad", "587954ABZ", "Test Comments", "shahzad.arshad@gmail.com", "Confiz123", "admin");

        given(userRepository.findById(userId)).willReturn(Optional.of(user));

        this.mockMvc.perform(get("/api/staffs/{id}", userId))
                .andExpect(status().isOk());
    }

    @WithMockUser(value = "spring")
    @Test
    void shouldReturn404WhenFindUserById() throws Exception {
        final Long userId = 18999L;
        given(userRepository.findById(userId)).willReturn(Optional.empty());

        this.mockMvc.perform(get("/api/staffs/{id}", userId))
                .andExpect(status().isNotFound());
    }



}