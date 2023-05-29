package ru.way2mars.java;

import java.util.List;

public class RubleKop {
    private static String delimiter = ".";
    long rubles;
    long kopeyki;

    public RubleKop(long rubles, long kopeyki) {
        this.rubles = rubles;
        this.kopeyki = kopeyki;
    }

    public static RubleKop fromString(String str){
        String[] parse = str.split(delimiter);
        return new RubleKop(0, 0);
    }


    @Override
    public String toString() {
        return String.valueOf(rubles) + delimiter + kopeyki;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RubleKop rubleKop = (RubleKop) o;

        if (rubles != rubleKop.rubles) return false;
        return kopeyki == rubleKop.kopeyki;
    }

    @Override
    public int hashCode() {
        int result = (int) (rubles ^ (rubles >>> 32));
        result = 31 * result + (int) (kopeyki ^ (kopeyki >>> 32));
        return result;
    }
}
