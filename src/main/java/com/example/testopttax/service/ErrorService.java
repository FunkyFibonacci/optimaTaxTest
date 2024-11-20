package com.example.testopttax.service;

import com.example.testopttax.exception.body.ErrorResponseBody;
import org.springframework.validation.BindingResult;

public interface ErrorService {
    ErrorResponseBody makeResponse(Exception exception);

    ErrorResponseBody makeResponse(BindingResult exception);
}
