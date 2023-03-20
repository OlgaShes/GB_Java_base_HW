package org.example;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class HW5_v2 {
    public static LinkedList<String[]> getNames(String namesLine) {
        LinkedList<String[]> result = new LinkedList<>();
        String[] people = namesLine.split(";");
        for (String person : people) {
            result.add(person.split(" "));
        }
        return result;
    }

    public static LinkedList<Object[]> getPhoneNumbers() {
        LinkedList<Object[]> phones = new LinkedList<>();
        for (int j = 0; j < (new Random().nextInt(3) + 1); j++) {
            StringBuilder number = new StringBuilder();
            number.append("+7");
            for (int i = 0; i < 10; i++) {
                number.append(new Random().nextInt(10));
            }
            Object[] phoneNumbers = new Object[2];
            phoneNumbers[0] = number.toString();
            phoneNumbers[1] = new Random().nextInt(10);
            phones.add(phoneNumbers);
        }
        return phones;
    }

    public static void printBook(HashMap<Integer, String[]> names,
                                 HashMap<Integer, LinkedList<Object[]>> phones) {
        for (Integer id : names.keySet()) {
            System.out.print(names.get(id)[0] + " " + names.get(id)[1] + ": ");
            for (Object[] number : phones.get(id)) {
                System.out.print(number[0] + "->" + number[1] + "; ");
            }
            System.out.println();
        }
    }

    public static void printBookByID(HashMap<Integer, String[]> names,
                                     HashMap<Integer, LinkedList<Object[]>> phones,
                                     LinkedList<Integer> listID) {
        for (Integer id : listID) {
            System.out.print(names.get(id)[0] + " " + names.get(id)[1] + ": ");
            for (Object[] number : phones.get(id)) {
                System.out.print(number[0] + "->" + number[1] + "; ");
            }
            System.out.println();
        }
    }

    public static LinkedList<Object[]> getPopularNames(HashMap<Integer, String[]> names) {
        HashMap<String, Integer> popularNames = new HashMap<>();
        LinkedList<Object[]> sortedPopularNames = new LinkedList<>();
        for (String[] name : names.values()) {
            popularNames.put(name[0], popularNames.getOrDefault(name[0], 0) + 1);
        }
        for (var name : popularNames.entrySet()) {
            Object[] item = new Object[2];
            item[0] = name.getKey();
            item[1] = name.getValue();
            sortedPopularNames.add(item);
        }
        sortedPopularNames.sort(new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                return (Integer) o2[1] - (Integer) o1[1];
            }
        });
        return sortedPopularNames;
    }

    public static LinkedList<Integer> sortBookByPopularNames(HashMap<Integer, String[]> people) {
        LinkedList<Integer> sortedID = new LinkedList<>();
        LinkedList<Object[]> popularNames = getPopularNames(people);
        for (Object[] name : popularNames) {
            for (Integer personID : people.keySet()) {
                if (people.get(personID)[0].equals(name[0])) {
                    sortedID.add(personID);
                }
            }
        }
        return sortedID;
    }

    public static LinkedList<Integer[]> getNumbersOfCalls(HashMap<Integer, LinkedList<Object[]>> phones) {
        LinkedList<Integer[]> numberOfCalls = new LinkedList<>();
        for (Integer id : phones.keySet()) {
            Integer[] temp = new Integer[]{id, 0};
            for (Object[] eachPhone : phones.get(id)) {
                temp[1] += (Integer) eachPhone[1];
            }
            numberOfCalls.add(temp);
        }
        return numberOfCalls;
    }

    public static LinkedList<Integer> sortByNumberOfCalls(HashMap<Integer, LinkedList<Object[]>> phones) {
        LinkedList<Integer> sortedID = new LinkedList<>();
        LinkedList<Integer[]> numberOfCalls = getNumbersOfCalls(phones);
        numberOfCalls.sort(new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                return (Integer) o2[1] - (Integer) o1[1];
            }
        });
        for (Integer[] number : numberOfCalls) {
            sortedID.add(number[0]);
        }
        return sortedID;
    }
    public static void main(String[] args) {
        HashMap<Integer, LinkedList<Object[]>> phones = new HashMap<>();
        HashMap<Integer, String[]> names = new HashMap<>();
        String namesLine = "Иван Иванов;Светлана Петрова;Кристина Белова;Анна Мусина;Анна Крутова;" +
                "Иван Юрин;Петр Лыков;Павел Чернов;Петр Чернышов;Мария Федорова;Марина Светлова;" +
                "Мария Савина;Мария Рыкова;Марина Лугова;Анна Владимирова;Иван Мечников;Петр Петин;Иван Ежов";
        ;
        LinkedList<String[]> people = getNames(namesLine);
        int id = 0;
        for (String[] person : people) {
            names.put(id, person);
            phones.put(id++, getPhoneNumbers());
        }
        printBook(names, phones);
        System.out.println();
        LinkedList<Object[]> popularNames = getPopularNames(names);
        popularNames.forEach(o -> System.out.println(o[0] + " встречается " + o[1] + " раз(а)"));
        System.out.println();
        System.out.println("Отсортировано по популярности имен:");
        printBookByID(names, phones, sortBookByPopularNames(names));
        System.out.println();
        System.out.println("Отсортировано по общему количеству звонков:");
        printBookByID(names, phones, sortByNumberOfCalls(phones));
    }
}


