package ru.denisov.entity;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;
import java.util.TreeMap;

@Component
public class ContactBook {
    private static final String PHONE_TEMPLATE_WITH_PLUS = "\\+7\\d{10}";
    private static final String PHONE_TEMPLATE = "8\\d{10}";
    private static final String CONTACT_TEMPLATE = "^.+;.+;.+";
    private static final String  EMAIL_TEMPLATE = "\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}";

    private final ObjectFactory<Contact> contactObjectFactory;
    private Map<String, Contact> contacts;
    @Value("${app.pathToSave}")
    private String pathToSave;

    public String showContacts() {
        StringBuilder builder = new StringBuilder();
        for(Contact contact : contacts.values()) {
            builder.append(MessageFormat.format("{0} | {1} | {2} ",
                    contact.getFullName(), contact.getPhoneNumber(), contact.getEmail()));
            builder.append("\n");
        }
        return builder.toString();
    }
    public void addContact(String contact) {
        final int FULL_NAME = 0;
        final int PHONE_NUMBER = 1;
        final int EMAIL = 2;
        String[] contactInfo = contact.split(";");
        try {
            checkAdd(contact);
            for(int i = 0; i < 3; i++){
                contactInfo[i] = contactInfo[i].trim();
            }
            checkEmail(contactInfo[EMAIL]);
            checkPhoneNumber(contactInfo[PHONE_NUMBER]);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        catch (Exception ex) {
            System.out.println("Неизвестная ошибка!");
            return;
        }
        if (contacts.containsKey(contactInfo[FULL_NAME])) {
            System.out.println("Такой контакт уже существует");
            return;
        }
        contacts.put(contactInfo[FULL_NAME],
                getContact(contactInfo[FULL_NAME], contactInfo[PHONE_NUMBER], contactInfo[EMAIL]));
    }

    private Contact getContact(String fullName, String phoneNumber, String email){
        Contact contact = contactObjectFactory.getObject();
        contact.setEmail(email);
        contact.setFullName(fullName);
        contact.setPhoneNumber(phoneNumber);
        return contact;
    }

    public void delete(String name){
        name = name.trim();
        Contact deleteContact = contacts.remove(name);
        if(deleteContact == null){
            System.out.println("Такого контакта не существует!");
        } else {
            System.out.println("Контакт удален!");
        }
    }

    public void saveContactsInFile(){
        try {
            FileWriter writer = new FileWriter(pathToSave);
            writer.write(showContacts());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkPhoneNumber(String phoneNumber) throws IllegalArgumentException{
        if (!phoneNumber.matches(PHONE_TEMPLATE) && !phoneNumber.matches(PHONE_TEMPLATE_WITH_PLUS)) {
            throw new IllegalArgumentException("Неверный формат телефона: " + phoneNumber);
        }
    }

    private void checkEmail(String email) throws IllegalArgumentException {
        if(!email.matches(EMAIL_TEMPLATE)) {
            throw new IllegalArgumentException("Неверный формат почты: " + email);
        }
    }
    public void checkAdd(String contact) throws IllegalArgumentException{
        if(!contact.matches(CONTACT_TEMPLATE)) {
            throw new IllegalArgumentException("Неверный формат добавления контакта: " + contact);
        }
    }

    public ContactBook(ObjectFactory<Contact> contactObjectFactory) {
        this.contactObjectFactory = contactObjectFactory;
        this.contacts = new TreeMap<>();
    }
}
