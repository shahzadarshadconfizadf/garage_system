package com.garagesystem;


import com.garagesystem.model.Job;
import com.garagesystem.repository.JobRepository;
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
public class JobController {
    private static final Logger log = LoggerFactory.getLogger(JobController.class);

    @Autowired
    JobRepository jobRepository;

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getAllJobs() {
        try {
            List<Job> jobs = new ArrayList<Job>();
            jobRepository.findAll().forEach(jobs::add);

            if (jobs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(jobs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobsById(@PathVariable("id") long id) {
        Optional<Job> jobData = jobRepository.findById(id);
        if (jobData.isPresent()) {
            return new ResponseEntity<>(jobData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/jobs")
    public ResponseEntity<Job> createJobs(@RequestBody Job job) {
        try {
            Job _job = jobRepository
                    .save(new Job(job.getBookingId(), job.getPartId(), job.getTechnicianId(), job.getPartsUsed(), job.getHoursWorked(), job.getDate()));
            return new ResponseEntity<>(_job, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable("id") long id, @RequestBody Job job) {
        Optional<Job> jobData = jobRepository.findById(id);
        if (jobData.isPresent()) {
            Job _job = jobData.get();
            _job.setBookingId(job.getBookingId());
            _job.setPartId(job.getPartId());
            _job.setTechnicianId(job.getTechnicianId());
            _job.setPartsUsed(job.getPartsUsed());
            _job.setHoursWorked(job.getHoursWorked());
            _job.setDate(job.getDate());

            return new ResponseEntity<>(jobRepository.save(_job), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/job/{id}")
    public ResponseEntity<HttpStatus> deleteJob(@PathVariable("id") long id) {
        try {
            jobRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/jobs")
    public ResponseEntity<HttpStatus> deleteAllJobs() {
        try {
            jobRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}