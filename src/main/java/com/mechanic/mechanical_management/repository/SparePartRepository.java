package com.mechanic.mechanical_management.repository;

import com.mechanic.mechanical_management.model.entities.SparePart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SparePartRepository extends JpaRepository<SparePart, Long> { }
