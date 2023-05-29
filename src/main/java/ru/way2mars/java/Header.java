package ru.way2mars.java;

import java.util.List;

public class Header {

    String accountNumber;
    String dateStart;
    String dateEnd;

    private Header(String stringAccount, String stringStart, String stringEnd){
        this.accountNumber = stringAccount;
        this.dateStart = stringStart;
        this.dateEnd = stringEnd;
    }

   public static Header fromStringArray(List<String> input){
        return new Header("", "", "");
    }
}
