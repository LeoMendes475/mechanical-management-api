package com.mechanic.mechanical_management.controller;

import com.mechanic.mechanical_management.dto.serviceorder.CreateServiceOrderRequest;
import com.mechanic.mechanical_management.dto.serviceorder.UpdateServiceOrderStatusRequest;
import com.mechanic.mechanical_management.model.entities.ServiceOrder;
import com.mechanic.mechanical_management.service.ServiceOrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-orders")
public class ServiceOrderController {

    private final ServiceOrderService service;

    public ServiceOrderController(ServiceOrderService service) {
        this.service = service;
    }

    @PostMapping
    public ServiceOrder save(@Valid @RequestBody CreateServiceOrderRequest request) {
        return service.create(request);
    }

    @PatchMapping("/{id}/status")
    public ServiceOrder updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateServiceOrderStatusRequest request
    ) {
        return service.updateStatus(id, request.getStatus());
    }

    @PostMapping("/{id}/spare-parts")
    public ServiceOrder addSparePart(
            @PathVariable Long id,
            @RequestParam Long sparePartId,
            @RequestParam Integer quantity
    ) {
        return service.createServiceOrder(id, sparePartId, quantity);
    }

    @GetMapping()
    public List<ServiceOrder> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ServiceOrder findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
