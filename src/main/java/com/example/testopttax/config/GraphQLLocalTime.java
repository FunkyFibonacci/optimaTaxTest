package com.example.testopttax.config;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class GraphQLLocalTime {
    public static GraphQLScalarType LocalTimeScalar() {
        return GraphQLScalarType.newScalar()
                .name("LocalTime")
                .description("A custom scalar type to handle LocalTime")
                .coercing(new Coercing<LocalTime, String>() {
                    @Override
                    public String serialize(Object dataFetcherResult) {
                        if (dataFetcherResult instanceof LocalTime) {
                            return ((LocalTime) dataFetcherResult).format(DateTimeFormatter.ISO_LOCAL_TIME);
                        }
                        return null;
                    }

                    @Override
                    public LocalTime parseValue(Object input) {
                        if (input instanceof String) {
                            return LocalTime.parse((String) input, DateTimeFormatter.ISO_LOCAL_TIME);
                        }
                        throw new IllegalArgumentException("Expected a String");
                    }

                    @Override
                    public LocalTime parseLiteral(Object input) {
                        if (input instanceof StringValue) {
                            String timeString = ((StringValue) input).getValue(); // Извлекаем строковое значение
                            try {
                                return LocalTime.parse(timeString, DateTimeFormatter.ISO_LOCAL_TIME);
                            } catch (DateTimeParseException e) {
                                throw new IllegalArgumentException("Invalid time format: " + timeString);
                            }
                        }
                        throw new IllegalArgumentException("Expected a StringValue");
                    }
                })
                .build();
    }
}
