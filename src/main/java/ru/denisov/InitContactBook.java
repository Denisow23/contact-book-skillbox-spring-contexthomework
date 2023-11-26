package ru.denisov;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import ru.denisov.entity.ContactBook;
import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class InitContactBook {
    @Value("${app.pathToInit}")
    private String initPath;
    private ObjectFactory<ContactBook> book;

    @PostConstruct
    public void initBook(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(initPath));
            String contact = reader.readLine();
            ContactBook contactBook = book.getObject();
            while(contact != null){
                contactBook.addContact(contact);
                contact = reader.readLine();
            }
            System.out.println("Контакты загружены из файла " + initPath + "!");
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла!");;
        }
    }

    public InitContactBook(ObjectFactory<ContactBook> book) {
        this.book = book;
    }

}
