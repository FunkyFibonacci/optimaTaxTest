package com.example.testopttax.service.impl;

import com.example.testopttax.record.income.IncomeCategoryInput;
import com.example.testopttax.record.income.IncomeCategoryResponceDto;
import com.example.testopttax.repo.IncomeCategoryRepository;
import com.example.testopttax.service.IncomeCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class IncomeCategoryServiceImpl implements IncomeCategoryService {
    private final IncomeCategoryRepository incomeCategoryRepository;
    @Override
    public IncomeCategoryResponceDto addIncomeCategory(IncomeCategoryInput input) {

        return null;
    }
}
