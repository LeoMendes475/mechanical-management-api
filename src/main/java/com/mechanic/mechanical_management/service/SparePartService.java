package com.mechanic.mechanical_management.service;

import com.mechanic.mechanical_management.dto.spartepart.CreateSparePartRequest;
import com.mechanic.mechanical_management.model.entities.SparePart;
import com.mechanic.mechanical_management.repository.SparePartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SparePartService {
    public SparePartService(SparePartRepository repo) { this.repo = repo; }
    private final SparePartRepository repo;

    public List<SparePart> listAll() { return repo.findAll(); }
    public SparePart save(CreateSparePartRequest m) { return repo.save(m); }
    public SparePart listById(Long id) { return repo.findById(id).orElseThrow(); }
    public void delete(Long id) { repo.deleteById(id); }
}