package com.garagesystem;


import com.garagesystem.model.Customer;
import com.garagesystem.repository.CustomerRepository;
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
public class CustomerController {
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        try {
            List<Customer> customers = new ArrayList<Customer>();

            if (firstName == null && lastName == null)
                customerRepository.findAll().forEach(customers::add);
            else
                customerRepository.findByFirstNameAndLastNameContaining(firstName, lastName).forEach(customers::add);

            if (customers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id) {
        Optional<Customer> customerData = customerRepository.findById(id);

        if (customerData.isPresent()) {
            return new ResponseEntity<>(customerData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try {

            Customer _Customer = customerRepository
                    .save(new Customer(customer.getCustomerTitle(), customer.getFirstName(), customer.getLastName(), customer.getAddressLine1(), customer.getAddressLine2(), customer.getCity(), customer.getCounty(),customer.getPostcode(),customer.getMobilePhone(),customer.getHomePhone(),customer.getEmailAddress()));
            return new ResponseEntity<>(_Customer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer) {
        Optional<Customer> CustomerData = customerRepository.findById(id);

        if (CustomerData.isPresent()) {
            Customer _Customer = CustomerData.get();
            _Customer.setCustomerTitle(customer.getCustomerTitle());
            _Customer.setFirstName(customer.getFirstName());
            _Customer.setLastName(customer.getLastName());
            _Customer.setAddressLine1(customer.getAddressLine1());
            _Customer.setAddressLine2(customer.getAddressLine2());
            _Customer.setCity(customer.getCity());
            _Customer.setCounty(customer.getCounty());
            _Customer.setPostcode(customer.getPostcode());
            _Customer.setMobilePhone(customer.getMobilePhone());
            _Customer.setHomePhone(customer.getHomePhone());
            _Customer.setEmailAddress(customer.getEmailAddress());

            return new ResponseEntity<>(customerRepository.save(_Customer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") long id) {
        try {
            customerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/customers")
    public ResponseEntity<HttpStatus> deleteAllCustomers() {
        try {
            customerRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}