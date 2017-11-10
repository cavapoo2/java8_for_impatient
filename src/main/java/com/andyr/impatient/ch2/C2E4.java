package com.andyr.impatient.ch2;

import java.util.Arrays;
import java.util.stream.IntStream;
//note you can find out what happens from Arrays.stream(values) using jshell. it more or less hints
//what the result is from Arrays.stream(values). (not so with intelli j with this version)
//
public class C2E4 {
    public static void main(String[] args){
        int []values = {1,4,9,16};
        IntStream is = Arrays.stream(values);
        is.forEach(System.out::println);
    }
}
