package com.mechanic.mechanical_management.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.mechanic.mechanical_management.model.entities.Vehicle;
import com.mechanic.mechanical_management.service.VehicleService;
import com.mechanic.mechanical_management.dto.vehicle.CreateVehicleRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    public VehicleController(VehicleService service) { this.service = service; }
    private final VehicleService service;

    @PostMapping
    public Vehicle create(@Valid @RequestBody CreateVehicleRequest request) {
        return service.create(request);
    }

    @GetMapping()
    public List<Vehicle> listAll() { return service.listAll(); }

    @GetMapping("/{id}")
    public Vehicle listById(@PathVariable Long id) {
        return service.listById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}