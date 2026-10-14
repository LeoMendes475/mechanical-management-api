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
    public Mechanic create(CreateMechanicRequest request) {
        Mechanic mechanic = new Mechanic();
        mechanic.setName(request.getName());
        mechanic.setSpecialty(request.getSpecialty());
        return repo.save(mechanic);
    }
    public Mechanic listById(Long id) { return repo.findById(id).orElseThrow(); }
    public void delete(Long id) { repo.deleteById(id); }
}
