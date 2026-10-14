package com.mechanic.mechanical_management.controller;

import com.mechanic.mechanical_management.dto.client.CreateClientRequest;
import com.mechanic.mechanical_management.model.entities.Client;
import com.mechanic.mechanical_management.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    public ClientController(ClientService service) { this.service = service; }
    private final ClientService service;

    @GetMapping
    public List<Client> listAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Client findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Client create(@Valid @RequestBody CreateClientRequest request) {
        return service.save(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}