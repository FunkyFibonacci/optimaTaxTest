package com.example.testopttax.repo;

import com.example.testopttax.model.TaxRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.desktop.OpenFilesEvent;
import java.util.Optional;

public interface TaxRateRepository extends JpaRepository<TaxRate, Long> {
    Optional<TaxRate> findTaxRateByCountryIdAndIncomeCategoryId(Long countryId, Long incomeCategoryId);
}
