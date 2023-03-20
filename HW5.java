package org.example;
import java.util.*;

public class HW5 {

    public static LinkedList<String[]> getNames(String namesLine) {
        LinkedList<String[]> result = new LinkedList<>();
        String[] people = namesLine.split(";");
        for (String person: people) {
            result.add(person.split(" "));
        }
        return result;
    }
    public static Object[] getPhoneNumber() {
        StringBuilder number = new StringBuilder();
        number.append("+7");
        for (int i = 0; i < 10; i++) {
            number.append(new Random().nextInt(10));
        }
        Object[] phoneNumbers = new Object[2];
        phoneNumbers[0] = number.toString();
        phoneNumbers[1] = new Random().nextInt(10);
        return phoneNumbers;
    }
    public static void printBook(HashMap<String[], LinkedList<Object[]>> book) {
        for (String[] n: book.keySet()) {
            System.out.print(n[0] + " " + n[1] + ": ");
            for (Object[] number: book.get(n)) {
                System.out.print(number[0] + "->" + number[1] + "; ");
            }
            System.out.println();
        }
    }
    public static HashMap<String, Integer> getPopularNames(HashMap<String[], LinkedList<Object[]>> book) {
        HashMap<String, Integer> popularNames = new HashMap<>();
        for (String[] names: book.keySet()) {
            popularNames.put(names[0], popularNames.getOrDefault(names[0], 0) + 1);
        }
        return popularNames;
    }
    public static LinkedList<Object[]> sortedPopularNames(HashMap<String, Integer> namesList) {
        LinkedList<Object[]> popularNames = new LinkedList<>();
        for (var name: namesList.entrySet()) {
            Object[] item = new Object[2];
            item[0] = name.getKey();
            item[1] = name.getValue();
            popularNames.add(item);
        }
        popularNames.sort(new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                return (Integer) o2[1] - (Integer) o1[1];
            }
        });
        return popularNames;
    }
    public static void sortBookByPopularNames(HashMap<String[], LinkedList<Object[]>> book, LinkedList<Object[]> names) {
        for (Object[] name: names) {
            for (String[] person: book.keySet()) {
                if (person[0].equals((String) name[0])) {
                    System.out.println(person[0] + " " + person[1]);
                }
            }
        }
    }
    public static void main(String[] args) {
        HashMap<String[], LinkedList<Object[]>> phonebook = new HashMap<>();
        String names = "Иван Иванов;Светлана Петрова;Кристина Белова;Анна Мусина;Анна Крутова;" +
                "Иван Юрин;Петр Лыков;Павел Чернов;Петр Чернышов;Мария Федорова;Марина Светлова;" +
                "Мария Савина;Мария Рыкова;Марина Лугова;Анна Владимирова;Иван Мечников;Петр Петин;Иван Ежов";;
        LinkedList<String[]> people = getNames(names);
        for (int i = 0; i < people.size(); i++) {
            LinkedList<Object[]> phones = new LinkedList<>();
            for (int j = 0; j < (new Random().nextInt(3)+1); j++) {
                phones.add(getPhoneNumber());
            }
            phonebook.put(people.get(i), phones);
        }
        printBook(phonebook);
        LinkedList<Object[]> popularNames = sortedPopularNames(getPopularNames(phonebook));
        popularNames.forEach(o -> System.out.println(o[0] + " встречается " + o[1] + " раз(а)"));
        sortBookByPopularNames(phonebook, popularNames);
    }


}
