package com.example.testopttax.config;

import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import graphql.schema.idl.RuntimeWiring;
import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.text.SimpleDateFormat;

@Configuration
public class ScalarConfiguration {
//    @Bean
//    public RuntimeWiring.Builder runtimeWiringBuilder() {
//        return RuntimeWiring.newRuntimeWiring()
//                .scalar(GraphQLLocalTime.LocalTimeScalar());
//    }

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder.scalar(GraphQLLocalTime.LocalTimeScalar());
    }
}
