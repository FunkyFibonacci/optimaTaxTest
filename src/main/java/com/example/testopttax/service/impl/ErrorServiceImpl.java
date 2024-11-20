package com.example.testopttax.service.impl;


import com.example.testopttax.exception.body.ErrorResponseBody;
import com.example.testopttax.service.ErrorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ErrorServiceImpl implements ErrorService {
    @Override
    public ErrorResponseBody makeResponse(Exception exception) {
        String message = exception.getMessage();
        log.debug(Arrays.toString(exception.getStackTrace()));
        return ErrorResponseBody.builder()
                .title(message)
                .reasons(Map.of("errors", List.of(message)))
                .build();
    }

    @Override
    public ErrorResponseBody makeResponse(BindingResult exception) {
        Map<String, List<String>> reasons = new HashMap<>();
        exception.getFieldErrors().stream()
                .filter(e -> e.getDefaultMessage() != null)
                .forEach(e -> {
                    List<String> errors = new ArrayList<>();
                    errors.add(e.getDefaultMessage());
                    if (!reasons.containsKey(e.getField())) {
                        reasons.put(e.getField(), errors);
                    } else {
                        reasons.get(e.getField()).addAll(errors);
                    }
                });
        return ErrorResponseBody.builder()
                .title("Error response body")
                .reasons(reasons)
                .build();
    }
}
