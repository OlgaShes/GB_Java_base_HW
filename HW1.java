package org.example;

import java.util.Arrays;
import java.util.Random;

public class HW1 {
    public static int getNumber(int maxLevel) {
        return new Random().nextInt(maxLevel);
    }

    public static int getBitNumber(int number) {
        return Integer.toBinaryString(number).length();
    }

    public static int[] getMultiples(int divider, int minLevel, int maxLevel, boolean ifMultiple) {
        int[] arr = new int[maxLevel - minLevel];
        int count = 0;
        for (int j = minLevel; j < maxLevel; j++) {
            if (ifMultiple == (j % divider == 0))
                arr[count++] = j;
        }
        return Arrays.copyOf(arr, count);
    }

    public static void main(String[] args) {

        int i = getNumber(2000);
        // System.out.println(i);
        int n = getBitNumber(i);
        // System.out.println(n);
        int[] m1 = getMultiples(n, i, Short.MAX_VALUE, true);
        // System.out.println(Arrays.toString(m1));
        int[] m2 = getMultiples(n, Short.MIN_VALUE, i, false);
        // System.out.println(Arrays.toString(m2));

    }
}
