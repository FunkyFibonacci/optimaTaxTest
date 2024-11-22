package com.example.testopttax.controller.graphql;

import com.example.testopttax.record.incomeCategory.IncomeCategoryInput;
import com.example.testopttax.record.incomeCategory.IncomeCategoryResponceDto;
import com.example.testopttax.service.IncomeCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IncomeCategoryController {
    private final IncomeCategoryService incomeCategoryService;

    @MutationMapping
    public IncomeCategoryResponceDto addIncomeCategory(@Argument("input")IncomeCategoryInput input){
        return incomeCategoryService.addIncomeCategory(input);
    }

    @QueryMapping
    public List<IncomeCategoryResponceDto> getAllCategories(){
        return incomeCategoryService.getAllCategories();
    }
    @QueryMapping
    public IncomeCategoryResponceDto getIncomeCategoryById(@Argument Long id){
        return incomeCategoryService.getIncomeCategoryById(id);
    }
    @QueryMapping
    public IncomeCategoryResponceDto getIncomeCategoryByName(@Argument String name){
        return incomeCategoryService.getIncomeCategoryByName(name);
    }
}
