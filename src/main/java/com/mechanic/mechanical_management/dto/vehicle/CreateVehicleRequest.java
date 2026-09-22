package com.mechanic.mechanical_management.dto.vehicle;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateVehicleRequest {

    @NotBlank
    private String licensePlate;

    @NotBlank
    private String model;

    @NotBlank
    private String brand;

    @NotNull
    @Positive
    private Integer year;

    @Positive
    private Integer mileage;

    @NotNull
    private Long clientId;
}