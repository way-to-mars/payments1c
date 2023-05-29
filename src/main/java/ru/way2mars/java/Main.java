package ru.way2mars.java;

public class Main {
    public static void main(String[] args) {
        Reader1C reader = Reader1C.fromFile("C:\\Users\\reception\\Desktop\\source1.txt");

        System.out.println(Reader1C.lastState);

        if(reader == null){
            System.out.println(Reader1C.lastState);
            return;
        }

        System.out.println(reader.getSum());
    }
}