package com.mechanic.mechanical_management.service;

import com.mechanic.mechanical_management.exception.DuplicateResourceException;
import com.mechanic.mechanical_management.model.entities.Client;
import com.mechanic.mechanical_management.model.entities.Vehicle;
import com.mechanic.mechanical_management.repository.VehicleRepository;
import com.mechanic.mechanical_management.repository.ClientRepository;
import com.mechanic.mechanical_management.dto.vehicle.CreateVehicleRequest;
import com.mechanic.mechanical_management.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    private final VehicleRepository repo;
    private final ClientRepository clientRepository;

    public VehicleService(
            VehicleRepository repo,
            ClientRepository clientRepository
    ) {
        this.repo = repo;
        this.clientRepository = clientRepository;
    }

    public List<Vehicle> listAll() { return repo.findAll(); }
    public Vehicle create(CreateVehicleRequest request) {
        Vehicle vehicle = new Vehicle();

        Optional<Vehicle> plateExist = repo.findByLicensePlateIgnoreCase(request.getLicensePlate());
        if (plateExist.isPresent()) {
            throw new DuplicateResourceException("Placa " + request.getLicensePlate()  + " já cadastrada");
        }

        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente " + request.getClientId() + " não encontrado"));
        vehicle.setClient(client);

        vehicle.setLicensePlate(request.getLicensePlate());
        vehicle.setModel(request.getModel());
        vehicle.setYear(request.getYear());
        vehicle.setMileage(request.getMileage());
        vehicle.setBrand(request.getBrand());

        return repo.save(vehicle);
    }
    public Vehicle listById(Long id) { return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Veiculo " + id + " não encontrado")); }
    public void delete(long id) { repo.deleteById(id); }
}
