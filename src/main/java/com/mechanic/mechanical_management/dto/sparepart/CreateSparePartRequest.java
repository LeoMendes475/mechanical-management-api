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

    @NotBlank(message = "Name is required")
    private String name;

    private String code;

    @NotNull(message = "Quantity is mandatory")
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;

    @NotNull(message = "Selling price is mandatory")
    @Positive(message = "Price must be greater than zero")
    private BigDecimal salePrice;
}