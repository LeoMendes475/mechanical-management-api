package com.mechanic.mechanical_management.repository;

import com.mechanic.mechanical_management.model.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> { }
