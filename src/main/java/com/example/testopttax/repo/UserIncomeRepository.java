package com.example.testopttax.repo;

import com.example.testopttax.model.User;
import com.example.testopttax.model.UserIncome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserIncomeRepository extends JpaRepository<UserIncome, Long> {
    List<UserIncome> findUserIncomeByUser(User user);
}
