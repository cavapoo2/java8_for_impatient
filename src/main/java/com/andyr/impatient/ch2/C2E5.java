package com.andyr.impatient.ch2;

import java.util.stream.Stream;

public class C2E5  {
    public static void main(String[] args) {
        Stream<Long> rs = C2E5.randGen(System.currentTimeMillis(),25214903917l,11l,(long)Math.pow(2,48));
        rs.limit(20).forEach(System.out::println);

    }
    public static Stream<Long> randGen(long seed,long a,long c,long m) {
        return Stream.iterate(seed,v -> (a * v + c) % m);
    }
}
