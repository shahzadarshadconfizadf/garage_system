package com.garagesystem;


import com.garagesystem.model.Booking;
import com.garagesystem.repository.BookingRepository;
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
public class BookingController {
    private static final Logger log = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    BookingRepository bookingRepository;

    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getAllBookings() {
        try {
            List<Booking> bookings = new ArrayList<Booking>();
            bookingRepository.findAll().forEach(bookings::add);

            if (bookings.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(bookings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("id") long id) {
        Optional<Booking> bookingData = bookingRepository.findById(id);
        if (bookingData.isPresent()) {
            return new ResponseEntity<>(bookingData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/bookings")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        try {
            Booking _booking = bookingRepository
                    .save(new Booking(booking.getCustomerId(), booking.getGarageId(), booking.getVehicleId(), booking.getServiceAdvisorId(), booking.getBookingDate(), booking.getBookingUpdatedDate(), booking.getBookingComments()));
            return new ResponseEntity<>(_booking, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/bookings/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable("id") long id, @RequestBody Booking booking) {
        Optional<Booking> bookingData = bookingRepository.findById(id);
        if (bookingData.isPresent()) {
            Booking _booking = bookingData.get();
            _booking.setCustomerId(booking.getCustomerId());
            _booking.setGarageId(booking.getGarageId());
            _booking.setVehicleId(booking.getVehicleId());
            _booking.setServiceAdvisorId(booking.getServiceAdvisorId());
            _booking.setBookingDate(booking.getBookingDate());
            _booking.setBookingUpdatedDate(booking.getBookingUpdatedDate());
            _booking.setBookingComments(booking.getBookingComments());

            return new ResponseEntity<>(bookingRepository.save(_booking), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/bookings/{id}")
    public ResponseEntity<HttpStatus> deleteBooking(@PathVariable("id") long id) {
        try {
            bookingRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/bookings")
    public ResponseEntity<HttpStatus> deleteAllBookings() {
        try {
            bookingRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}