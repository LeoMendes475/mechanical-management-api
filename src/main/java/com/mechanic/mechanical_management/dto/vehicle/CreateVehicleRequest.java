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

    @NotBlank(message = "Placa é obrigatória")
    private String licensePlate;

    @NotBlank(message = "Modelo é obrigatório")
    private String model;

    @NotBlank(message = "Marca é obrigatória")
    private String brand;

    @NotNull(message = "Ano é obrigatório")
    @Positive(message = "Ano deve ser positivo")
    private Integer year;

    @Positive(message = "Quilometragem deve ser positiva")
    private Integer mileage;

    @NotNull(message = "Cliente é obrigatório")
    private Long clientId;
}