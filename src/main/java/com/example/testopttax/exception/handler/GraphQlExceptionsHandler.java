package com.example.testopttax.exception.handler;

import com.example.testopttax.exception.CustomException;
import graphql.GraphQLError;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GraphQlExceptionsHandler {
    @GraphQlExceptionHandler
    public GraphQLError handleException(CustomException exception){
        return GraphQLError.newError()
                .message(exception.getMessage())
                .build();
    }

    @GraphQlExceptionHandler
    public GraphQLError handleValidationException(ValidationException exception) {
        return GraphQLError.newError()
                .message(exception.getMessage())
                .build();
    }


}
