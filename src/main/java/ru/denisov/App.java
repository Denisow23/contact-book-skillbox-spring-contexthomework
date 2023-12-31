package ru.denisov;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.denisov.configs.AppConfig;
import ru.denisov.entity.ContactBook;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ContactBook book = context.getBean(ContactBook.class);
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("1 - add, 2- delete, 3 - view, 4 - save to file");
            int a;
            try {
                a = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Такой команды не существует!");
                continue;
            }
            switch (a) {
                case (1): {
                    System.out.println("Введите контакт");
                    scanner.nextLine();
                    String contact = scanner.nextLine();
                    book.addContact(contact);
                    System.out.println("Контакт записан");
                    break;
                }
                case (2) : {
                    System.out.println("Введите имя");
                    scanner.nextLine();
                    String contact = scanner.nextLine();
                    book.delete(contact);
                    break;
                }
                case (3) : {
                    System.out.println(book.showContacts());
                    break;
                }
                case (4) : {
                    book.saveContactsInFile();
                    break;
                }
                default:
                    System.out.println("Такой команды не существует!");
            }
        }
    }
}
