package com.example.testopttax.repo;

import com.example.testopttax.model.IncomeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeCategoryRepository extends JpaRepository<IncomeCategory,Long> {
}
