package com.garagesystem.repository;

import com.garagesystem.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JobRepository extends JpaRepository<Job, Long> {
}
