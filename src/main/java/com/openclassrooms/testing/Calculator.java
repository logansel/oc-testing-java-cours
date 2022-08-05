package com.openclassrooms.testing;

import java.util.HashSet;
import java.util.Set;

public class Calculator {
    public static void main(String[] args) {
        System.out.println(add(50,50));
        System.out.println(multiply(50,50));
        System.out.println(test1(123456789));
    }

    public static int add(int a, int b) {
        return a + b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public void longCalculation() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Set<Integer> digitsSet(int number) {
        Set<Integer> integers = new HashSet<Integer>();
        String numberString = String.valueOf(number);

        for (int i = 0; i < numberString.length(); i++) {
            if (numberString.charAt(i) != '-') {
                integers.add(Integer.parseInt(numberString, i, i + 1, 10));
            }
        }
        return integers;
    }

    public static String test1(int nb) {
        String nbafter = String.format("%,d", 123456789);
        return nbafter;
    }
}



