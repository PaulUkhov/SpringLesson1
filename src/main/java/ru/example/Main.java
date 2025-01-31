package ru.example;

import com.google.gson.Gson; //Использования зависимости Gson для сериализации

import java.io.*;
//Создать проект с использованием Maven или Gradle, добавить в него несколько зависимостей и написать код,
// использующий эти зависимости.
//Задание:
//        1. Создайте новый Maven или Gradle проект, следуя инструкциям из блока 1 или блока 2.
//        2. Добавьте зависимости org.apache.commons:commons-lang3:3.12.0 и com.google.code.gson:gson:2.8.6.
//        3. Создайте класс Person с полями firstName, lastName и age.
//        4. Используйте библиотеку commons-lang3 для генерации методов toString, equals и hashCode.
//5. Используйте библиотеку gson для сериализации и десериализации объектов класса Person в формат JSON.
public class Main {
    public static void main(String[] args) {
        Person person = new Person("Paul", "Ukhov", 18,123);
        Gson gson = new Gson();
        System.out.println("Текстовой формат JSON - Основное задание");
        try (FileWriter fileWriter = new FileWriter("person.json")) {
            gson.toJson(person, fileWriter);
            System.out.println(" Данные записаны в файл");

            String serPerson = gson.toJson(person);
            System.out.println(serPerson);

            Person person2 = gson.fromJson(serPerson, Person.class);
            System.out.println(person2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Бинарный формат - Использование ручной сериализации");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.bin"))) {
            oos.writeObject(person);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.bin"))) {
            Person desOis = (Person) ois.readObject();
            System.out.println(desOis);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
