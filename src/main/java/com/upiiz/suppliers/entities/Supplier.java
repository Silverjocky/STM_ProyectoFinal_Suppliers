package com.upiiz.suppliers.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;
    private String contact;
    private String name;
    private String phone;

    //
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "expense_id")
    // private Long expenseId;

    // @Column(name = "expense_date", nullable = false)
    // private LocalDate expenseDate;

    // @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    // private BigDecimal amount;

    // @Column(columnDefinition = "TEXT")
    // private String description;
}