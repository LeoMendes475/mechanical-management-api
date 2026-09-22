package com.mechanic.mechanical_management.service;

import com.mechanic.mechanical_management.model.entities.Vehicle;
import com.mechanic.mechanical_management.repository.VehicleRepository;
import com.mechanic.mechanical_management.dto.vehicle.CreateVehicleRequest;

import java.util.List;

public class VehicleService {
    public VehicleService(VehicleRepository repo) { this.repo = repo; }
    private final VehicleRepository repo;

    public List<Vehicle> listAll() { return repo.findAll(); }
    public Vehicle create(CreateVehicleRequest v) { return repo.save(v); }
    public Vehicle listById(Long id) { return repo.findById(id).orElseThrow(); }
    public void delete(long id) { repo.deleteById(id); }
}
