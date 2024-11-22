package com.example.testopttax.exception.handler;

import com.example.testopttax.exception.CustomException;
import graphql.GraphQLError;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GraphQlExceptionsHandler {
    @GraphQlExceptionHandler
    public GraphQLError handleException(CustomException exception){
        return GraphQLError.newError()
                .message(exception.getMessage())
                .build();
    }
}
