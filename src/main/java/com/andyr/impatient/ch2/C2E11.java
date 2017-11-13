package com.andyr.impatient.ch2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class C2E11 {
    public static void main(String[] args){
        List<ArrayList<String>> list = new ArrayList<>();
        list.add(new ArrayList<>(Arrays.asList("Hi", "Hello", "OK")));
        list.add(new ArrayList<>(Arrays.asList("Today", "Tomorrow")));
        list.add(new ArrayList<>(Arrays.asList("Good", "Great", "Nice", "Lovely", "Perfect")));
        List<String> res = C2E11.conv(list.stream());
        for(String s: res){
            System.out.println(s);
        }

    }

    public static List<String> conv(Stream<ArrayList<String>> stream) {
        String [] arr = stream.flatMap(Collection::stream).toArray(String[]::new);
        IntStream is = IntStream.range(0,arr.length);
        List<String> res = new ArrayList<>(Arrays.asList(new String[arr.length]));
        is.parallel().forEach(i -> res.set(i,arr[i]));
        return res;
    }

}
