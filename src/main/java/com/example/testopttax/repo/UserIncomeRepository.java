package com.example.testopttax.repo;

import com.example.testopttax.model.UserIncome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserIncomeRepository extends JpaRepository<UserIncome, Long> {
}
