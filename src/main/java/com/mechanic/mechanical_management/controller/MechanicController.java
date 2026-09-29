package com.mechanic.mechanical_management.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.mechanic.mechanical_management.service.MechanicService;
import com.mechanic.mechanical_management.model.Mechanic;
import com.mechanic.mechanical_management.dto.mechanic.CreateMechanicRequest;

@RestController
@RequestMapping("/mechanic")
public class MechanicController{
    private final MechanicService service;

    public MechanicController(MechanicService service) {
        this.service = service;
    }

    @PostMapping
    public Mechanic save(@Valid @RequestBody CreateMechanicRequest request) {
        return service.create(request);
    }

    @GetMapping()
    public List<Mechanic> ListAll() { service.listAll(); }

    @GetMapping("/{id}")
    public Mechanic ListById(@PathVariable Long id) { this.service.listById(id);}

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}