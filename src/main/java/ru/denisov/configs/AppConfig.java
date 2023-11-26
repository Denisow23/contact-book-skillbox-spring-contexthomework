package ru.denisov.configs;

import org.springframework.context.annotation.*;


@ComponentScan("ru.denisov")
@PropertySource("classpath:application.properties")
public class AppConfig {
}
