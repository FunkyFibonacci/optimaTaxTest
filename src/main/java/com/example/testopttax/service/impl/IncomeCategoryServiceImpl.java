package com.example.testopttax.service.impl;

import com.example.testopttax.exception.CustomException;
import com.example.testopttax.model.IncomeCategory;
import com.example.testopttax.record.incomeCategory.IncomeCategoryInput;
import com.example.testopttax.record.incomeCategory.IncomeCategoryResponceDto;
import com.example.testopttax.repo.IncomeCategoryRepository;
import com.example.testopttax.service.IncomeCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class IncomeCategoryServiceImpl implements IncomeCategoryService {
    private final IncomeCategoryRepository incomeCategoryRepository;
    @Override
    public IncomeCategoryResponceDto addIncomeCategory(IncomeCategoryInput input) {
        if (input.name().isBlank() || input.name().isEmpty()){
            log.error("Категория не может быть пустой!");
            throw new CustomException("Категория пустая!");
        }
        if (incomeCategoryRepository.findIncomeCategoryByNameIgnoreCase(input.name()).isPresent()){
            log.error("Такая категория дохода:{} уже есть!", input.name());
            throw new CustomException("Такая категория уже существует!");
        }
        IncomeCategory incomeCategory = new IncomeCategory();
        incomeCategory.setName(input.name());
        IncomeCategory savedCategory = incomeCategoryRepository.save(incomeCategory);

        return mapModelToResponceDto(savedCategory);
    }

    @Override
    public List<IncomeCategoryResponceDto> getAllCategories() {
        return incomeCategoryRepository.findAll().stream().map(incomeCategory -> new IncomeCategoryResponceDto(
                incomeCategory.getId(),
                incomeCategory.getCreatedAt(),
                incomeCategory.getUpdatedAt(),
                incomeCategory.getName()
        )).collect(Collectors.toList());
    }

    @Override
    public IncomeCategoryResponceDto getIncomeCategoryByName(String name) {
        IncomeCategory incomeCategoryFromDb = incomeCategoryRepository.findIncomeCategoryByNameIgnoreCase(name).orElseThrow(() -> new CustomException("Не найдена категория по названию!"));
        return mapModelToResponceDto(incomeCategoryFromDb);
    }

    @Override
    public IncomeCategoryResponceDto getIncomeCategoryById(Long id) {
        IncomeCategory incomeCategoryFromDb = findById(id);
        return mapModelToResponceDto(incomeCategoryFromDb);
    }

    @Override
    public IncomeCategoryResponceDto mapModelToResponceDto(IncomeCategory incomeCategory){
        return new IncomeCategoryResponceDto(incomeCategory.getId(), incomeCategory.getCreatedAt(),incomeCategory.getUpdatedAt(),incomeCategory.getName());
    }

    @Override
    public IncomeCategory findById(Long id) {
        return incomeCategoryRepository.findById(id).orElseThrow(() -> {
            log.error("Не найдена категория доода с id: {}", id);
            return new CustomException("Не найдена категория дохода с id:"+id);
        });
    }

}
