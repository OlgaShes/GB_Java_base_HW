package org.example;

public class HW2 {
    // Напишите программу, чтобы проверить вхождение в строке (содержащей все
    // символы
    // другой строки).
    public static boolean findString(String line, String subline) {
        return line.contains(subline);
    }

    // Напишите программу, чтобы проверить, являются ли две данные строки вращением
    // друг друга (вхождение в обратном порядке).
    public static boolean findReverceString(String line, String subline) {
        subline = new StringBuilder(subline).reverse().toString();
        return line.contains(subline);
    }

    // Напишите программу, чтобы перевернуть строку с помощью рекурсии
    public static String reverseString(String line) {
        if (line.length() == 1)
            return line;
        return reverseString(line.substring(1, line.length())) + line.charAt(0);
    }

    // Даны два числа, составить строки с операциями сложения, вычитания и умножения
    // например: 3 + 56 = 59 3 – 56 = -53 3 * 56 = 168
    public static String getMathOperation(int number1, int number2, String operation) {
        StringBuilder builder = new StringBuilder();
        builder
                .append(number1)
                .append(" " + operation + " ")
                .append(number2)
                .append(" = ");
        if (operation == "+")
            builder.append(number1 + number2);
        else if (operation == "*")
            builder.append(number1 * number2);
        else if (operation == "-")
            builder.append(number1 - number2);
        else if (operation == "/")
            builder.append(number1 / number2);
        return builder.toString();
    }

    // Замените символ “=” на слово “равно”. Замените символ “=” на слово “равно”.
    // Используйте методы StringBuilder.insert(),StringBuilder.deleteCharAt().
    public static String changeEqualsSign(String str) {
        StringBuilder builder = new StringBuilder(str);
        while (builder.indexOf("=") != -1) {
            builder.insert(builder.indexOf("="), "равно");
            builder.deleteCharAt(builder.indexOf("="));
        }
        return builder.toString();
    }

    // Замените символ “=” на слово “равно”. Используйте методы
    // StringBuilder.replace().
    public static String replaceEqualsSign(String str) {
        StringBuilder builder = new StringBuilder(str);
        while (builder.indexOf("=") != -1) {
            builder.replace(builder.indexOf("="), builder.indexOf("=") + 1, "равно");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(findString("qwertyuiop", "erty"));
        System.out.println(findReverceString("qwertyuiop", "oiuyt"));

        System.out.println(reverseString("987654321"));

        System.out.println(getMathOperation(3, 56, "+"));
        System.out.println(getMathOperation(3, 56, "-"));
        System.out.println(getMathOperation(3, 56, "*"));
        System.out.println(changeEqualsSign(getMathOperation(3, 56, "+")));
        System.out.println(replaceEqualsSign(getMathOperation(3, 56, "-")));

        // Сравнить время замены со строкой содержащей 10000 символов "=" средствами
        // String и StringBuilder.

        String testLine = "=".repeat(10_000);

        long begin = System.currentTimeMillis();
        String resultLine = replaceEqualsSign(testLine);
        long end = System.currentTimeMillis();
        System.out.println("StringBuilder --> " + (end - begin));

        begin = System.currentTimeMillis();
        resultLine = testLine.replaceAll("=", "равно");
        end = System.currentTimeMillis();
        System.out.println("String --> " + (end - begin));
    }
}
