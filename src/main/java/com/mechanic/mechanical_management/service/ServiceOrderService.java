package com.mechanic.mechanical_management.service;

import com.mechanic.mechanical_management.dto.serviceorder.CreateServiceOrderRequest;
import com.mechanic.mechanical_management.exception.InsufficientStockException;
import com.mechanic.mechanical_management.model.WorkOrderStatus;
import com.mechanic.mechanical_management.model.entities.Client;
import com.mechanic.mechanical_management.model.entities.Mechanic;
import com.mechanic.mechanical_management.model.entities.ServiceOrder;
import com.mechanic.mechanical_management.model.entities.ServiceOrderSparePart;
import com.mechanic.mechanical_management.model.entities.SparePart;
import com.mechanic.mechanical_management.model.entities.Vehicle;
import com.mechanic.mechanical_management.repository.ClientRepository;
import com.mechanic.mechanical_management.repository.ServiceOrderRepository;
import com.mechanic.mechanical_management.repository.ServiceOrderSparePartRepository;
import com.mechanic.mechanical_management.repository.SparePartRepository;
import com.mechanic.mechanical_management.repository.VehicleRepository;
import com.mechanic.mechanical_management.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceOrderService {
    private final ServiceOrderRepository serviceOrderRepository;
    private final SparePartRepository sparePartRepository;
    private final ServiceOrderSparePartRepository serviceOrderSparePartRepository;
    private final ClientRepository clientRepository;
    private final VehicleRepository vehicleRepository;

    public ServiceOrderService(
            ServiceOrderRepository serviceOrderRepository,
            SparePartRepository sparePartRepository,
            ServiceOrderSparePartRepository serviceOrderSparePartRepository,
            ClientRepository clientRepository,
            VehicleRepository vehicleRepository
    ) {
        this.serviceOrderRepository = serviceOrderRepository;
        this.sparePartRepository = sparePartRepository;
        this.serviceOrderSparePartRepository = serviceOrderSparePartRepository;
        this.clientRepository = clientRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public List<ServiceOrder> findAll() { return serviceOrderRepository.findAll(); }

    @Transactional
    public ServiceOrder save(ServiceOrder serviceOrder) {
        return serviceOrderRepository.save(serviceOrder);
    }

    // Recebe o DTO no formato "achatado" que o app Flutter envia e monta
    // as entidades relacionais (Client, Vehicle) antes de salvar a OS.
    @Transactional
    public ServiceOrder create(CreateServiceOrderRequest request) {
        Vehicle vehicle = null;

        if (request.getVehiclePlate() != null && !request.getVehiclePlate().isBlank()) {
            vehicle = vehicleRepository.findByLicensePlateIgnoreCase(request.getVehiclePlate())
                    .orElseGet(Vehicle::new);

            vehicle.setLicensePlate(request.getVehiclePlate());
            vehicle.setModel(request.getVehicleModel());
            vehicle.setYear(request.getVehicleYear());
            vehicle.setMileage(request.getMileage());

            if (request.getClientName() != null && !request.getClientName().isBlank()) {
                Client client = clientRepository.findByNameIgnoreCase(request.getClientName())
                        .orElseGet(() -> {
                            Client newClient = new Client();
                            newClient.setName(request.getClientName());
                            return clientRepository.save(newClient);
                        });
                vehicle.setClient(client);
            }

            vehicle = vehicleRepository.save(vehicle);
        }

        ServiceOrder serviceOrder = new ServiceOrder();
        serviceOrder.setVehicle(vehicle);
        serviceOrder.setStatus(WorkOrderStatus.OPEN);
        serviceOrder.setDescription(request.getDescription());
        serviceOrder.setTotalValue(request.getTotalValue());

        return serviceOrderRepository.save(serviceOrder);
    }

    @Transactional
    public ServiceOrder updateStatus(Long id, WorkOrderStatus status) {
        ServiceOrder order = serviceOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OS " + id + " não encontrada"));
        order.setStatus(status);
        return serviceOrderRepository.save(order);
    }

    @Transactional
    public ServiceOrder createServiceOrder(Long serviceOrderId, Long sparePartId, Integer quantity) {

        // busca a OS
        ServiceOrder order = serviceOrderRepository.findById(serviceOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("OS " + serviceOrderId + " não encontrada"));

        // busca a peça
        SparePart sparePart = sparePartRepository.findById(sparePartId)
                .orElseThrow(() -> new ResourceNotFoundException("peça " + sparePartId + " não encontrada"));

        // valida estoque
        if (sparePart.getQuantity() < quantity) {
            throw new InsufficientStockException(
                    "Estoque insuficiente. Disponível: " + sparePart.getQuantity()
            );
        }

        // desconta estoque
        sparePart.setQuantity(sparePart.getQuantity() - quantity);
        sparePartRepository.save(sparePart);

        // cria e salva o vínculo
        ServiceOrderSparePart orderSparePart = new ServiceOrderSparePart();
        orderSparePart.setServiceOrder(order);
        orderSparePart.setSparePart(sparePart);
        orderSparePart.setQuantity(quantity);
        orderSparePart.setUnitPrice(sparePart.getSalePrice());
        serviceOrderSparePartRepository.save(orderSparePart);

        // retorna a OS atualizada
        return serviceOrderRepository.findById(serviceOrderId).get();
    }

    public ServiceOrder findById(Long id) { return serviceOrderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ordem de serviço " + id + " não encontrado")); }
    public void delete(Long id) { serviceOrderRepository.deleteById(id); }
}
