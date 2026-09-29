package com.mechanic.mechanical_management.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.mechanic.mechanical_management.service.SparePartService;
import com.mechanic.mechanical_management.model.entities.SparePart;
import com.mechanic.mechanical_management.dto.spartepart.CreateSparePartRequest;

@RestController
@RequestMapping("/spare-part")
public class SparePartController{
    private final SparePartService service;

    public SparePartController(SparePartService service) {
        this.service = service;
    }

    @PostMapping
    public SparePart save(@Valid @RequestBody CreateSparePartRequest request) {
        return service.create(request);
    }

    @GetMapping()
    public List<SparePart> ListAll() { service.listAll(); }

    @GetMapping("/{id}")
    public SparePart ListById(@PathVariable Long id) { this.service.listById(id);}

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}