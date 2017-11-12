package com.andyr.impatient.ch2;

import com.andyr.impatient.Util;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class C2E9 {
    public static void main(String[] args) {

        joinStreams3(getNewStream().parallel()).forEach(System.out::println);
        joinStreams(getNewStream()).forEach(System.out::println);
    }

    static Stream<ArrayList<Integer>> getNewStream() {
        ArrayList<Integer> a1 = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        ArrayList<Integer> a2 = new ArrayList<>(Arrays.asList(11,12,13,14,15));
        ArrayList<Integer> a3 = new ArrayList<>(Arrays.asList(16,17,18,19,20));
        Stream.Builder<ArrayList<Integer>> builder = Stream.builder();
        builder.add(a1);
        builder.add(a2);
        builder.add(a3);
        return builder.build();
    }

    static <T> ArrayList<T> joinStreams(Stream<ArrayList<T>> al) {
       return al.reduce(new ArrayList<>(),(acc,lst) -> {
            ArrayList<T> l = new ArrayList<>(acc);
            l.addAll(lst);
            return l;
        });
    }
    static <T> ArrayList<T> joinStreams2(Stream<ArrayList<T>> al) {
       return al.reduce((acc,lst) -> {
            ArrayList<T> l = new ArrayList<>(acc);
            l.addAll(lst);
            return l;
        }).orElse(new ArrayList<>());
    }
    //more suitable for parallel stream
    static <T> ArrayList<T> joinStreams3(Stream<ArrayList<T>> al) {
       return al.reduce(new ArrayList<>(),(acc,lst) -> {
            ArrayList<T> l = new ArrayList<>(acc);
            l.addAll(lst);
            return l;
        },
        (acc,lst) -> {
            ArrayList<T> l = new ArrayList<>(acc);
            l.addAll(lst);
            return l;

        });
    }
}
