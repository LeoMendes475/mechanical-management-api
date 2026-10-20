package com.mechanic.mechanical_management.service;

import com.mechanic.mechanical_management.dto.sparepart.CreateSparePartRequest;
import com.mechanic.mechanical_management.model.entities.SparePart;
import com.mechanic.mechanical_management.repository.SparePartRepository;
import org.springframework.stereotype.Service;
import com.mechanic.mechanical_management.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class SparePartService {
    public SparePartService(SparePartRepository repo) { this.repo = repo; }
    private final SparePartRepository repo;

    public List<SparePart> listAll() { return repo.findAll(); }
    public SparePart create(CreateSparePartRequest request) {
        SparePart sparePart = new SparePart();
        sparePart.setName(request.getName());
        sparePart.setCode(request.getCode());
        sparePart.setQuantity(request.getQuantity());
        sparePart.setSalePrice(request.getSalePrice());
        return repo.save(sparePart);
    }
    public SparePart listById(Long id) { return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Part " + id + " not found.")); }
    public void delete(Long id) { repo.deleteById(id); }
}