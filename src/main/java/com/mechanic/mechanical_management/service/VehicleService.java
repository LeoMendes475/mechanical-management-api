package com.mechanic.mechanical_management.service;

import com.mechanic.mechanical_management.model.entities.Client;
import com.mechanic.mechanical_management.model.entities.Vehicle;
import com.mechanic.mechanical_management.repository.VehicleRepository;
import com.mechanic.mechanical_management.repository.ClientRepository;
import com.mechanic.mechanical_management.dto.vehicle.CreateVehicleRequest;

import java.util.List;

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
        Vehicle vehicle = null;
        if (request.getLicensePlate() != null && !request.getLicensePlate().isBlank()) {
            vehicle = repo.findByLicensePlateIgnoreCase(request.getLicensePlate())
                    .orElseGet(Vehicle::new);

            vehicle.setLicensePlate(request.getLicensePlate());
            vehicle.setModel(request.getModel());
            vehicle.setYear(request.getYear());
            vehicle.setMileage(request.getMileage());
            vehicle.setBrand(request.getBrand());

            Client client = clientRepository.findById(request.getClientId())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
            vehicle.setClient(client);

            vehicle = repo.save(vehicle);
        }

        return repo.save(vehicle);
    }
    public Vehicle listById(Long id) { return repo.findById(id).orElseThrow(); }
    public void delete(long id) { repo.deleteById(id); }
}
