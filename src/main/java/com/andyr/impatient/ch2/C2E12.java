package com.andyr.impatient.ch2;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class C2E12 {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("hello","bye","bonjour","how","why","what","when","","something","robust","empirically");
        AtomicInteger[] shortWords = new AtomicInteger[12];
        words.parallelStream().forEach(
                w -> {
                    int length = w.length();
                    if (length < 12){
                        AtomicInteger i = shortWords[length];
                        if (i == null){
                            i = new AtomicInteger();
                            shortWords[length] = i;
                        }
                        i.incrementAndGet();
                    }
                }
        );
        Arrays.asList(shortWords).forEach(System.out::println);
    }

}
