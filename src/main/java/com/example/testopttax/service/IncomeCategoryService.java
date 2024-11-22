package com.example.testopttax.service;

import com.example.testopttax.record.income.IncomeCategoryInput;
import com.example.testopttax.record.income.IncomeCategoryResponceDto;
import org.springframework.stereotype.Service;

@Service
public interface IncomeCategoryService {
    IncomeCategoryResponceDto addIncomeCategory(IncomeCategoryInput input);
}
