package com.garagesystem.repository;

import com.garagesystem.model.Parts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartsRepository extends JpaRepository<Parts, Long> {
}
