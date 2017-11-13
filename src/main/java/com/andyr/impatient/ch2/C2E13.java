package com.andyr.impatient.ch2;

import com.andyr.impatient.Util;

import java.util.List;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class C2E13 implements Util {
    public static void main(String[] args) {
        C2E13 obj = new C2E13();
        obj.action();

    }
    public void action() {
        List<String> words = getWordsAsList("alice.txt");
        words.parallelStream()
                .filter(w -> w.length() < 12)
                .collect(groupingBy(String::length,counting()))
                .forEach((k,v) -> System.out.printf("%d, %d\n",k,v));

    }
}
