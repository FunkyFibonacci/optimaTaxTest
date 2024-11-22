package com.example.testopttax.controller.graphql;

import com.example.testopttax.record.income.IncomeCategoryInput;
import com.example.testopttax.record.income.IncomeCategoryResponceDto;
import com.example.testopttax.service.IncomeCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class IncomeCategoryController {
    private final IncomeCategoryService incomeCategoryService;

    @MutationMapping
    public IncomeCategoryResponceDto addIncomeCategory(@Argument("input")IncomeCategoryInput input){
        return incomeCategoryService.addIncomeCategory(input);
    }
}
