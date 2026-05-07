package com.mechanic.mechanical_management.model.entities;

import com.mechanic.mechanical_management.model.WorkOrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ServiceOrderSparePart {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_order_id")
    private ServiceOrder serviceOrder;

    @ManyToOne
    @JoinColumn(name = "spare_part_id")
    private SparePart sparePart;

    @Min(1)
    private Integer quantity;

    private BigDecimal unitPrice;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;
}
