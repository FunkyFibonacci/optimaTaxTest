package com.example.testopttax.service;

import com.example.testopttax.model.User;
import com.example.testopttax.model.UserIncome;
import com.example.testopttax.record.userIncome.UserIncomeInput;
import com.example.testopttax.record.userIncome.UserIncomeResponceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserIncomeService {
    UserIncomeResponceDto addIncome(UserIncomeInput input);

    UserIncomeResponceDto mapModelToDto(UserIncome userIncome);

    List<UserIncome> getUserIncomesByUser(User user);
}
