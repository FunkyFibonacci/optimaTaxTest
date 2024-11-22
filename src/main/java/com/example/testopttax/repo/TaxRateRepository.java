package com.example.testopttax.repo;

import com.example.testopttax.model.TaxRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRateRepository extends JpaRepository<TaxRate, Long> {
}
