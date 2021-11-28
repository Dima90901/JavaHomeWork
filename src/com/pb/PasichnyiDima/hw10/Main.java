package com.pb.PasichnyiDima.hw10;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        NumBox <Float> floatNumBox = new NumBox<>(10);
        NumBox <Integer> integerNumBoxNumBox = new NumBox<>(15);
        System.out.println("Заполнение массива Float");
        int floatIndex = 0;
        while (floatIndex <= 5) {
            floatNumBox.add(1.5f + floatIndex, floatIndex);
            floatIndex++;
        }
        System.out.println("Заполнение массива Integer");
        int intIndex = 0;
        while (intIndex <= 5) {
            integerNumBoxNumBox.add(2 + intIndex, intIndex);
            intIndex++;
        }
        System.out.println("Для получения значения по индексу введие индекс:");
        Scanner scan = new Scanner(System.in);
        int index = scan.nextInt();
        System.out.println("Цифра по индексу floatNumBox: " + floatNumBox.get(index));
        System.out.println("Цифра по индексу integerNumBoxNumBox: " + integerNumBoxNumBox.get(index));
        System.out.println("Длинна массива floatNumBox: " + floatNumBox.length());
        System.out.println("Длинна массива integerNumBoxNumBox: " + integerNumBoxNumBox.length());
        System.out.println("Среднее арифметическое floatNumBox: " + floatNumBox.average());
        System.out.println("Среднее арифметическое integerNumBoxNumBox: " + integerNumBoxNumBox.average());
        System.out.println("Cумма всех элементов массива floatNumBox: " + floatNumBox.sum());
        System.out.println("Cумма всех элементов массива integerNumBoxNumBox: " + integerNumBoxNumBox.sum());
        System.out.println("Максимальный элемент массива floatNumBox: " + floatNumBox.max());
        System.out.println("Максимальный элемент массива integerNumBoxNumBox: " + integerNumBoxNumBox.max());
    }
}
