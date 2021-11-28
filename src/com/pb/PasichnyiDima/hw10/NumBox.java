package com.pb.PasichnyiDima.hw10;

import java.util.Scanner;

public class NumBox <T extends Number> {
    private T[] numbers;
    private int lenghtFlag;

    public NumBox(int size) {
        numbers = (T[]) new Number[size];
    }

    public void add(T num, int index) {
        if (index >= 0 && index <= numbers.length) {
            numbers[index] = num;
            lenghtFlag++;
        }
        else throw new ArrayIndexOutOfBoundsException();
    }

    public T get(int index) {
        return numbers[index];
    }

    public int length() {
        return lenghtFlag;
    }

    public double average() {
        int lenght = 0;
        double sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != null) {
                sum = numbers[i].doubleValue() + sum;
                lenght++;
            }
        }
        sum = sum / lenght;
        return sum;
    }

    public double sum() {
        double sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != null) {
                sum = numbers[i].doubleValue() + sum;
            }
        }
        return sum;
    }

    public T max() {
        T sum = null;;
        for (int i = 0; i < lenghtFlag-1; i++) {
            if (numbers[i] != null) {
                if (numbers[i].doubleValue() < numbers[i+1].doubleValue()) {
                    sum = numbers[i+1];
                }
            }
        }
        return sum;
    }
}
