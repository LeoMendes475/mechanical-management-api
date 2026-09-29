package com.mechanic.mechanical_management.service;

import com.mechanic.mechanical_management.dto.mechanic.CreateMechanicRequest;
import com.mechanic.mechanical_management.model.entities.Mechanic;
import com.mechanic.mechanical_management.repository.MechanicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MechanicService {
    public MechanicService(MechanicRepository repo) { this.repo = repo; }
    private final MechanicRepository repo;

    public List<Mechanic> listAll() { return repo.findAll(); }
    public Mechanic save(CreateMechanicRequest m) { return repo.save(m); }
    public Mechanic listById(Long id) { return repo.findById(id).orElseThrow(); }
    public void delete(Long id) { repo.deleteById(id); }
}
