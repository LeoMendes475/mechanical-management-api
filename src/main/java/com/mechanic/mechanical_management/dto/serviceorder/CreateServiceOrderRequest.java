package com.mechanic.mechanical_management.dto.serviceorder;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateServiceOrderRequest {

    @NotBlank
    private String description;

    private BigDecimal totalValue;

    private String vehicleModel;
    private String vehiclePlate;
    private Integer vehicleYear;
    private Integer mileage;

    private String clientName;
}
