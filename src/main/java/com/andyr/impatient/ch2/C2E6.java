package com.andyr.impatient.ch2;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class C2E6 {
    public static void main(String[] args) {
        Stream<Character> cs = charStream("hello World");
        cs.forEach(System.out::println);

    }
    public static Stream<Character> charStream(String s) {
        //return Stream.iterate(0,v -> v + 1).limit(s.length()).map(i -> s.charAt(i));
        return IntStream.range(0,s.length()).mapToObj(s::charAt);
    }
}
