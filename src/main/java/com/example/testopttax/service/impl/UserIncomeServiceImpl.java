package com.example.testopttax.service.impl;

import com.example.testopttax.model.IncomeCategory;
import com.example.testopttax.model.User;
import com.example.testopttax.model.UserIncome;
import com.example.testopttax.record.userIncome.UserIncomeInput;
import com.example.testopttax.record.userIncome.UserIncomeResponceDto;
import com.example.testopttax.repo.UserIncomeRepository;
import com.example.testopttax.service.IncomeCategoryService;
import com.example.testopttax.service.UserIncomeService;
import com.example.testopttax.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserIncomeServiceImpl implements UserIncomeService {
    private final UserIncomeRepository userIncomeRepository;
    private final UserService userService;
    private final IncomeCategoryService incomeCategoryService;

    @Override
//    @Transactional(rollbackOn = Exception.class)
    public UserIncomeResponceDto addIncome(UserIncomeInput input) {
        User authUser = userService.getUserBySecurityHolder(SecurityContextHolder.getContext().getAuthentication());
        IncomeCategory incomeCategory = incomeCategoryService.findById(input.incomeCategoryId());
        BigDecimal amountBigInt = BigDecimal.valueOf(input.amount());

        UserIncome userIncome = new UserIncome();
        userIncome.setUser(authUser);
        userIncome.setIncomeCategory(incomeCategory);
        userIncome.setAmount(amountBigInt);

        UserIncome userIncomeSaved = userIncomeRepository.save(userIncome);
        return mapModelToDto(userIncomeSaved);
    }

    @Override
    public UserIncomeResponceDto mapModelToDto(UserIncome userIncome) {
        return new UserIncomeResponceDto(userIncome.getId(),
                userIncome.getCreatedAt(), userIncome.getUpdatedAt(),
                userService.mapModelToDto(userIncome.getUser()),
                incomeCategoryService.mapModelToResponceDto(userIncome.getIncomeCategory()),
                userIncome.getAmount());
    }
}
