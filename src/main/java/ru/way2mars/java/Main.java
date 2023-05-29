package ru.way2mars.java;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Reader1C reader = Reader1C.fromFile("D:\\Code\\payments1c\\src\\source1.txt");

        if(reader == null){
            System.out.println("Can't read the input file");
            return;
        }


    }
}