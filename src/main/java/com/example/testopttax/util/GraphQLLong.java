package com.example.testopttax.util;

import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;

public class GraphQLLong {

    public static GraphQLScalarType LongType = GraphQLScalarType.newScalar()
            .name("Long")
            .coercing(new Coercing<Long, Long>() {
                @Override
                public Long serialize(Object dataFetcherResult) {
                    return (Long) dataFetcherResult;
                }

                @Override
                public Long parseValue(Object input) {
                    return Long.parseLong(input.toString());
                }

                @Override
                public Long parseLiteral(Object input) {
                    return Long.parseLong(input.toString());
                }
            })
            .build();
}
