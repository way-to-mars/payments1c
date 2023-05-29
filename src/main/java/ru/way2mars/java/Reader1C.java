package ru.way2mars.java;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import java.util.List;

public class Reader1C {

    private final List<String> lines;
    private Reader1C(List<String> file_lines){
        lines = file_lines;
    }

    public static Reader1C fromFile(String filename){
            Path path = Paths.get(filename);
            List<String> file_lines = new ArrayList<>();

            try (Stream<String> stream = Files.lines(path, Charset.forName("cp1251"))) {
                stream.forEach(file_lines::add);
            }
            catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        return new Reader1C(file_lines);
    }

    public Header getHeader(){
        return Header.fromStringArray(this.lines);
    }
}
