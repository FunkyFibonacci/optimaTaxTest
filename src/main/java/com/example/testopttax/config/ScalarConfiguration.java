package com.example.testopttax.config;

import graphql.schema.idl.RuntimeWiring;
import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScalarConfiguration {

    @Bean
    public RuntimeWiring.Builder runtimeWiringBuilder() {
        return RuntimeWiring.newRuntimeWiring()
                .scalar(ExtendedScalars.GraphQLLong);
    }
}
