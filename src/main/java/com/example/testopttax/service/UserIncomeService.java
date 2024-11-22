package com.example.testopttax.service;

import com.example.testopttax.model.UserIncome;
import com.example.testopttax.record.userIncome.UserIncomeInput;
import com.example.testopttax.record.userIncome.UserIncomeResponceDto;
import org.springframework.stereotype.Service;

@Service
public interface UserIncomeService {
    UserIncomeResponceDto addIncome(UserIncomeInput input);

    UserIncomeResponceDto mapModelToDto(UserIncome userIncome);
}
