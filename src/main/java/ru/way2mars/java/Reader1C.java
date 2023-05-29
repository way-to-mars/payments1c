package ru.way2mars.java;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Stream;

import java.util.List;

public class Reader1C {
    private enum State {
        OK,
        IO_EXCEPTION,
        WRONG_FORMAT,
        EMPTY_FILE,
    }

    public static State lastState;
    private final List<String> lines;

    private Date dateStart;
    private Date dateEnd;

    private Reader1C(List<String> file_lines) {
        lines = file_lines;
        lastState = State.OK;
    }

    public static Reader1C fromFile(String filename) {
        Path path = Paths.get(filename);
        List<String> file_lines = new ArrayList<>();

        try (Stream<String> stream = Files.lines(path, Charset.forName("cp1251"))) {
            stream.forEach(file_lines::add);
        } catch (IOException e) {
            e.printStackTrace();
            Reader1C.lastState = State.IO_EXCEPTION;
            return null;
        }

        if (file_lines.isEmpty()) {
            Reader1C.lastState = State.EMPTY_FILE;
            return null;
        }

        if (!checkFormat(file_lines)) {
            Reader1C.lastState = State.WRONG_FORMAT;
            return null;
        }

        Reader1C instance = new Reader1C(file_lines);
        instance.readHeader();

        return instance;
    }

    private Date readHeader() {
        for (String line : this.lines) {
            if (line.startsWith("ДатаСоздания=")) {
                System.out.println("Date of creation = " + line.substring(line.indexOf('=') + 1));
                continue;
            }
            if (line.startsWith("ДатаНачала=")) {
                System.out.println("Start date = " + line.substring(line.indexOf('=') + 1));
                continue;
            }
            if (line.startsWith("ДатаКонца=")) {
                System.out.println("End date = " + line.substring(line.indexOf('=') + 1));
                continue;
            }
            if (line.startsWith("РасчСчет=")) {
                System.out.println("Account = " + line.substring(line.indexOf('=') + 1));
                continue;
            }
            if (line.startsWith("Документ=")) {
                System.out.println("Documents section at line #");
                break;
            }
        }

        return new Date();
    }


    public double getSum(){
        double result = 0.0;
        for (String line : this.lines){
            if (line.startsWith("Сумма=")){
                String str_number = line.split("=")[1];
                result += Double.parseDouble(str_number);
            }
        }
        return result;
    }


    private static boolean checkFormat(List<String> lines) {
        String internalTag = "1CClientBankExchange";
        boolean isInternalFlag = false;

        String encodingTag = "Кодировка=Windows";
        boolean isEncodingTag = false;

        String senderTag = "Отправитель=Бухгалтерия";
        boolean isSenderTag = false;

        for (String str : lines) {
            if (isInternalFlag && isEncodingTag && isSenderTag) return true;

            if (!isInternalFlag)
                if (str.equals(internalTag)) isInternalFlag = true;

            if (!isEncodingTag)
                if (str.equals(encodingTag)) isEncodingTag = true;

            if (!isSenderTag)
                if (str.startsWith(senderTag)) isSenderTag = true;
        }
        return isInternalFlag && isEncodingTag && isSenderTag;
    }

}
