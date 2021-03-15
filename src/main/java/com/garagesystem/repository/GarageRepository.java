package com.garagesystem.repository;

import com.garagesystem.model.Garage;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GarageRepository extends JpaRepository<Garage, Long> {
}
