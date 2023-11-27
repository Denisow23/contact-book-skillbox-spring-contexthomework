package ru.denisov.configs;

import org.springframework.context.annotation.*;


@ComponentScan("ru.denisov")
@PropertySource(value = "classpath:application.yml", factory = YmlPropertySourceFactory.class)
public class AppConfig {
}
