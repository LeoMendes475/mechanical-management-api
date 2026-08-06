package com.mechanic.mechanical_management.model.entities;

import com.mechanic.mechanical_management.model.WorkOrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class ServiceOrder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne @JoinColumn(name = "mechanic_id")
    private Mechanic mechanic;

    @Enumerated(EnumType.STRING)
    private WorkOrderStatus status;

    private String description;
    private BigDecimal totalValue;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

        @LastModifiedDate
        private LocalDateTime updatedAt;

        private LocalDateTime deletedAt;
}
