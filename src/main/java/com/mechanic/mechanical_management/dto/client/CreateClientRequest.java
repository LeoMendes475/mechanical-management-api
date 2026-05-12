package com.mechanic.mechanical_management.dto.client;

import com.mechanic.mechanical_management.UserRoleEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreateClientRequest {
    @NotBlank
    private String name;
    private String cpf;
    private String phone;
    private String email;
    private String address;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private UserRoleEnum role;
}
