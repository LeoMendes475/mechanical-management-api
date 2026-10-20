package com.mechanic.mechanical_management.dto.mechanic;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMechanicRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Specialty is mandatory")
    private String specialty;
}