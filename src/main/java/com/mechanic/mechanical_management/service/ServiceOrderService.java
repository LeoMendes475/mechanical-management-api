package com.mechanic.mechanical_management.service;

import com.mechanic.mechanical_management.model.entities.Mechanic;
import com.mechanic.mechanical_management.model.entities.ServiceOrder;
import com.mechanic.mechanical_management.model.entities.ServiceOrderSparePart;
import com.mechanic.mechanical_management.model.entities.SparePart;
import com.mechanic.mechanical_management.repository.ServiceOrderRepository;
import com.mechanic.mechanical_management.repository.ServiceOrderSparePartRepository;
import com.mechanic.mechanical_management.repository.SparePartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServiceOrderService {
    private final ServiceOrderRepository serviceOrderRepository;
    private final SparePartRepository sparePartRepository;
    private final ServiceOrderSparePartRepository serviceOrderSparePartRepository;

    public ServiceOrderService(
            ServiceOrderRepository serviceOrderRepository,
            SparePartRepository sparePartRepository,
            ServiceOrderSparePartRepository serviceOrderSparePartRepository
    ) {
        this.serviceOrderRepository = serviceOrderRepository;
        this.sparePartRepository = sparePartRepository;
        this.serviceOrderSparePartRepository = serviceOrderSparePartRepository;
    }

    public List<ServiceOrder> findAll() { return serviceOrderRepository.findAll(); }

    @Transactional
    public ServiceOrder save(ServiceOrder serviceOrder) {
        return serviceOrderRepository.save(serviceOrder);
    }

    @Transactional
    public ServiceOrder createServiceOrder(Long serviceOrderId, Long sparePartId, Integer quantity) {

        // busca a OS
        ServiceOrder order = serviceOrderRepository.findById(serviceOrderId)
                .orElseThrow(() -> new RuntimeException("OS não encontrada"));

        // busca a peça
        SparePart sparePart = sparePartRepository.findById(sparePartId)
                .orElseThrow(() -> new RuntimeException("Peça não encontrada"));

        // valida estoque
        if (sparePart.getQuantity() < quantity) {
            throw new RuntimeException(
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

    public ServiceOrder findById(Long id) { return serviceOrderRepository.findById(id).orElseThrow(); }
    public void delete(Long id) { serviceOrderRepository.deleteById(id); }
}
