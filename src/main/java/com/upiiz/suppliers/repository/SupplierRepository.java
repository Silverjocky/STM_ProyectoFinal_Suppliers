package com.upiiz.suppliers.repository;

import com.upiiz.suppliers.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}