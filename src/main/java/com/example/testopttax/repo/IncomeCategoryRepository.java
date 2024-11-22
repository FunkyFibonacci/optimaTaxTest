package com.example.testopttax.repo;

import com.example.testopttax.model.IncomeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IncomeCategoryRepository extends JpaRepository<IncomeCategory,Long> {
    Optional<IncomeCategory> findIncomeCategoryByNameIgnoreCase(String name);
}
