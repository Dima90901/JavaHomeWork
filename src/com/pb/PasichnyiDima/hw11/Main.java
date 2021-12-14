package com.pb.PasichnyiDima.hw11;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {
        List<Integer> phones = new ArrayList<>();
        phones.add(991234567);
        phones.add(991234568);
        phones.add(991234569);
        TelephoneBook firstPerson = new TelephoneBook("Petrov Dima", LocalDate.of(2000, 5, 2), phones, "Dneprovskaya str 1");
        TelephoneBook secondPerson = new TelephoneBook("Ivanov Vova", LocalDate.of(1999, 4, 12), phones, "Kievskaya str 2");
        TelephoneBook thirdPerson = new TelephoneBook("Sidorov Kolya", LocalDate.of(1998, 9, 24), phones, "Odesskaya str 3");


        List<TelephoneBook> telephoneBook = new ArrayList<>();
        telephoneBook.add(firstPerson);
        telephoneBook.add(secondPerson);
        telephoneBook.add(thirdPerson);
        telephoneBook.remove(2);

        Scanner scan = new Scanner(System.in);
        System.out.println("Введите Фамилию и имя для поиска:");
        String str = scan.nextLine();
        boolean search = Search(str, telephoneBook);
        if (search == true) {
            System.out.println("Такой пользователь есть в телефонной книге");
        }
        else System.out.println("Не найден в телефонной книге");

        System.out.println("Вывод всех записей, сортированных по фамилии и имени");
        telephoneBook.stream().collect(Collectors.groupingBy(TelephoneBook::getName));
        telephoneBook.stream().forEach(System.out::println);

    System.out.println("Введите фамилию и имя, кого редактировать:");
    String strEdit = scan.nextLine();
    System.out.println("Введите телефоны (без 380) поочередно, которые необходимо изменить у вышеуказанного пользователя, для завершения ввода введите 0:");
    int flag = 1;
    List<Integer> newPhones = new ArrayList<>();
    while (flag == 1) {
        int newPhone = scan.nextInt();
        if (newPhone == 0) {
            break;
        }
        newPhones.add(newPhone);
    }
    editPhones(strEdit, telephoneBook, newPhones);
    telephoneBook.stream().forEach(System.out::println);

    System.out.println("Дата последнего изменения (создания/редактирования):");
    for (int i = 0; i < telephoneBook.size(); i++) {
            System.out.println(telephoneBook.get(i) + " дата последней операции с данным контактом: " + telephoneBook.get(i).getEditCreateTime());
        }

    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    SimpleModule module = new SimpleModule();
    module.addSerializer(LocalDate.class, new LocalDateSerializer());
    module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
    module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
    module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
    mapper.registerModule(module);
    String telephoneBookJSON = mapper.writeValueAsString(telephoneBook);


    Path path = Files.createFile(Paths.get("D:\\TestCreateFilesJava\\TelephoneBook.txt"));
    try (BufferedWriter writer = Files.newBufferedWriter(path)) {
        writer.write(telephoneBookJSON);
        }
    catch (Exception e) {
            e.getStackTrace();
        }
    Scanner scanFile = new Scanner(new File(String.valueOf(path)));
    String fullTextFile = "";
    while (scanFile.hasNextLine()) {
        fullTextFile =  fullTextFile + scanFile.nextLine() + "\n";
    }
    System.out.println(fullTextFile);
    List<TelephoneBook> telephoneBooks2 = mapper.readValue(fullTextFile, new TypeReference<List<TelephoneBook>>() {});
        System.out.println(telephoneBooks2);
    }


    static boolean Search(String str, List<TelephoneBook> telephoneBook) {
        for(int i = 0; i < telephoneBook.size(); i++) {
            if (str.equals(telephoneBook.get(i).getName())) {
                return true;
            }
        }
        return false;
    }

    static void editPhones (String str, List<TelephoneBook> telephoneBook, List<Integer> phonesNew) {
        for(int i = 0; i < telephoneBook.size(); i++) {
            if (str.equals(telephoneBook.get(i).getName())) {
                telephoneBook.get(i).editPhoneNumbers(phonesNew);
            }
        }
    }
}
