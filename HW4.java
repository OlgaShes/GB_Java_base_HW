package org.example;
import java.util.*;
public class HW4 {
    static Scanner iScanner = new Scanner(System.in);

    public static String getLastName() {
        System.out.println("Введите фамилию: ");
        String name = iScanner.nextLine();
        return name;
    }

    public static String getFirstName() {
        System.out.println("Введите имя: ");
        String name = iScanner.nextLine();
        return name;
    }

    public static String getMiddleName() {
        System.out.println("Введите отчество: ");
        String name = iScanner.nextLine();
        return name;
    }

    public static int getAge() {
        System.out.println("Введите возраст: ");
        int age = 0;
        while (age == 0) {
            try {
                age = Integer.parseInt(iScanner.nextLine());
            } catch (Exception e) {
                System.out.println("Возраст может содержать только цифры");
            }
        }
        return age;
    }

    public static Boolean getGender() {
        System.out.println("Введите пол (m/f): ");
        String gender_male = ";male;m;мужской;мужчина;м";
        String gender_female = ";female;f;женский;женщина;ж";
        String gender = "0";
        Boolean result = null;
        while (gender.equals("0")) {
            gender = iScanner.nextLine();
            if (gender_male.contains(";" + gender.toLowerCase()))
                result = true;
            else if (gender_female.contains(";" + gender.toLowerCase()))
                result = false;
            else {
                System.out.println("Введите пол в формате m/f или м/ж");
                gender = "0";
            }
        }
        return result;
    }

    public static Object[] getData() {
        Object[] data = new Object[5];
        data[0] = getLastName();
        data[1] = getFirstName();
        data[2] = getMiddleName();
        data[3] = getAge();
        data[4] = getGender();
        return data;
    }

    public static LinkedList<Integer> getID(LinkedList<Object[]> list) {
        LinkedList<Integer> idList = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            idList.add(i);
        }
        return idList;
    }

//    public static void printData(LinkedList<Object[]> data) {
//        for (Object[] people : data) {
//            System.out.print(people[0] + " ");
//            System.out.print(((String) people[1]).toUpperCase().charAt(0) + ".");
//            System.out.print(((String) people[2]).toUpperCase().charAt(0) + ". ");
//            System.out.print(people[3]);
//            if ((Boolean) people[4]) {
//                System.out.println('М');
//            } else {
//                System.out.println('Ж');
//            }
//        }
//    }

//    public static void sortByAge(LinkedList<Object[]> data) {
//        data.sort(new Comparator<Object[]>() {
//            @Override
//            public int compare(Object[] t0, Object[] t1) {
//                return (Integer) t0[3] - (Integer) t1[3];
//            }
//        });
//
//    }

//      public static void sortByGender(LinkedList<Object[]> data) {
//        data.sort(new Comparator<Object[]>() {
//            @Override
//            public int compare(Object[] t0, Object[] t1) {
//                if ((Boolean) t0[4] && !(Boolean) t1[4])
//                    return 1;
//                else if (!(Boolean) t0[4] && (Boolean) t1[4])
//                    return -1;
//                return 0;
//            }
//        });
//    }
    public static void printByIDData(LinkedList<Integer> idList, LinkedList<Object[]> data) {
        for (int id: idList) {
            System.out.print(data.get(id)[0] + " ");
            System.out.print(((String) data.get(id)[1]).toUpperCase().charAt(0) + ".");
            System.out.print(((String) data.get(id)[2]).toUpperCase().charAt(0) + ". ");
            System.out.print(data.get(id)[3]);
            if ((Boolean) data.get(id)[4]) {
                System.out.println('М');
            } else {
                System.out.println('Ж');
            }
        }
    }

    public static LinkedList<Integer> sortIDByAge(LinkedList<Integer> id, LinkedList<Object[]> data) {
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.size() - i - 1; j++) {
                if ((Integer)data.get(id.get(j))[3] > (Integer)data.get(id.get(j+1))[3]) {
                    int temp = id.set(j, id.get(j + 1));
                    id.set(j+1, temp);
                }
            }
        }
        return id;
    }

    public static LinkedList<Integer> sortIDByGender(LinkedList<Integer> id, LinkedList<Object[]> data) {
        int i = 0;
        int count = data.size();
        while (i < count) {
            if ((Boolean) data.get(id.get(i))[4]) {
                id.add(id.remove(i--));
                count--;
            }
            i++;
        }
        return id;
    }
    public static void main(String[] args) {
        LinkedList<Object[]> people = new LinkedList<>();
        String command = "";
        while (!command.equals("q")) {
            people.add(getData());
            System.out.println("Для выхода из редактирования введите 'q', для продолжения любую клавишу");
            command = iScanner.nextLine();
        }
        LinkedList<Integer> peopleID = getID(people);
        printByIDData(peopleID, people);
        System.out.println("Для сортировки по возрасту введите 1,\n" +
                "для сортировки по полу, затем по возрасту возрасту введите 2,\n" +
                "для сортировки по возрасту, затем по полу введите 3,\n" +
                "для сортировки только по полу нажмите 4,\n" +
                "для завершения работы нажмите любую иную клавишу");
        command = "";
        command = iScanner.nextLine();
        switch (command) {
            case "1":
                printByIDData(sortIDByAge(peopleID, people), people);
                break;
            case "2":
                printByIDData(sortIDByGender(sortIDByAge(peopleID, people), people) , people);
                break;
            case "3":
                printByIDData(sortIDByAge(sortIDByGender(peopleID, people), people) , people);
                break;
            case "4":
                printByIDData(sortIDByGender(peopleID, people), people);
                break;
        }
    }
}
