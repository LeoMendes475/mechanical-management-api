package com.mechanic.mechanical_management.dto.sparepart;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSparePartRequest {

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    private String code;

    @NotNull(message = "Quantidade é obrigatória")
    @Min(value = 0, message = "Quantidade não pode ser negativa")
    private Integer quantity;

    @NotNull(message = "Preço de venda é obrigatório")
    @Positive(message = "Preço deve ser maior que zero")
    private BigDecimal salePrice;
}