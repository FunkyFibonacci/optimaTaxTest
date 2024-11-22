package com.example.testopttax.service;

import com.example.testopttax.model.IncomeCategory;
import com.example.testopttax.record.income.IncomeCategoryInput;
import com.example.testopttax.record.income.IncomeCategoryResponceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IncomeCategoryService {
    IncomeCategoryResponceDto addIncomeCategory(IncomeCategoryInput input);

    List<IncomeCategoryResponceDto>  getAllCategories();

    IncomeCategoryResponceDto getIncomeCategoryByName(String name);

    IncomeCategoryResponceDto getIncomeCategoryById(Long id);
    IncomeCategoryResponceDto mapModelToResponceDto(IncomeCategory incomeCategory);

    IncomeCategory findById(Long id);
}
