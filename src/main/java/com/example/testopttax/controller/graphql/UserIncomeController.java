package com.example.testopttax.controller.graphql;

import com.example.testopttax.record.userIncome.UserIncomeInput;
import com.example.testopttax.record.userIncome.UserIncomeResponceDto;
import com.example.testopttax.service.UserIncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserIncomeController {
    private final UserIncomeService userIncomeService;

    @MutationMapping
    public UserIncomeResponceDto addIncome(@Argument("input")UserIncomeInput input){
        return userIncomeService.addIncome(input);
    }
}
