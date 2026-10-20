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

    @NotBlank(message = "Sign is mandatory")
    private String licensePlate;

    @NotBlank(message = "Model is mandatory")
    private String model;

    @NotBlank(message = "Brand is mandatory")
    private String brand;

    @NotNull(message = "Year is mandatory")
    @Positive(message = "The year should be positive")
    private Integer year;

    @Positive(message = "Mileage should be positive")
    private Integer mileage;

    @NotNull(message = "Customer is required")
    private Long clientId;
}