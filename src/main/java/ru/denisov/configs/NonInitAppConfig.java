package ru.denisov.configs;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("non-init")
public class NonInitAppConfig {
}
