package com.example.testopttax.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class AppConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Tax")
                        .version("1.0.0"));
    }

    @Bean
    public CommandLineRunner generatePasswordHash(PasswordEncoder passwordEncoder) {
        return args -> {
            String rawPassword = "qweqwe";
            String hashedPassword = passwordEncoder.encode(rawPassword);
            System.out.println("Хэш'" + rawPassword + "' " + hashedPassword);
        };
    }

}
