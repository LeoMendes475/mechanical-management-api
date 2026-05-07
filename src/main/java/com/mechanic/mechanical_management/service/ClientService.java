package com.mechanic.mechanical_management.service;

import com.mechanic.mechanical_management.model.entities.Client;
import com.mechanic.mechanical_management.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    public ClientService(ClientRepository repo) { this.repo = repo; }
    private final ClientRepository repo;

    public List<Client> findAll() { return repo.findAll(); }
    public Client save(Client c)   { return repo.save(c); }
    public Client findById(Long id)     { return repo.findById(id).orElseThrow(); }
    public void delete(Long id)       { repo.deleteById(id); }
}