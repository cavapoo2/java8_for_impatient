package com.andyr.impatient.ch2;

import com.andyr.impatient.Util;
import org.junit.Test;

import java.util.ArrayList;

public class C2E2 implements Util {
    @Test
    @Override
    public void action() {
        ArrayList<String> words = getWordsAsArrayList("alice.txt");
        words.parallelStream().filter(s -> s.length() > 12).limit(5).forEach(System.out::println);
    }
}
