package ru.denisov.configs;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.annotation.*;
import ru.denisov.InitContactBook;
import ru.denisov.entity.ContactBook;

@Configuration
@Profile("init")
@PropertySource("classpath:application-init.properties")
public class InitAppConfig {
    @Bean
    public static InitContactBook initContactBook(ObjectFactory<ContactBook> bookObjectFactory){
        return new InitContactBook(bookObjectFactory);
    }
}
