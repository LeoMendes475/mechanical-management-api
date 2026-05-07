package com.mechanic.mechanical_management.repository;

import com.mechanic.mechanical_management.model.entities.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MechanicRepository extends JpaRepository<Mechanic, Long> { }
