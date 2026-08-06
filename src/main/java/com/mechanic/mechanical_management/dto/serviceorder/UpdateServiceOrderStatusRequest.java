package com.mechanic.mechanical_management.dto.serviceorder;

import com.mechanic.mechanical_management.model.WorkOrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateServiceOrderStatusRequest {

    @NotNull
    private WorkOrderStatus status;
}
