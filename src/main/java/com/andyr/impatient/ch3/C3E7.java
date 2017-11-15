package com.andyr.impatient.ch3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumSet;

import static org.junit.Assert.assertArrayEquals;

enum CompareEnums {
    CASE_INSENSITIVE,
    REVERSE,
    SPACE_INSENSITIVE
}
public class C3E7 {
    public static void main(String[] args) {

        String[] data = { "Hello","aB Bc"," Rf pS "};
        //note this just sorts array. it lowercases and removes space before doing the sort. The end result
        //preserves the case and spaces if any
        Arrays.sort(data,compare(EnumSet.of(CompareEnums.CASE_INSENSITIVE,CompareEnums.SPACE_INSENSITIVE)));
        //check sorted array
        Arrays.asList(data).forEach(System.out::println);
        if (Arrays.equals(data,new String[] {
                "aB Bc",
                "Hello",
                " Rf pS "
        }) ){
            System.out.println("Yes arrays are equal");
        } else {
            System.out.println("No arrays are not equal");
        }

        Arrays.sort(data,compare(EnumSet.noneOf(CompareEnums.class)));

        Arrays.asList(data).forEach(System.out::println);
         if (Arrays.equals(data,new String[] {
                 " Rf pS ",
                 "Hello",
                "aB Bc",
        }) ){
            System.out.println("Yes arrays are equal");
        } else {
            System.out.println("No arrays are not equal");
        }

    }
    public static Comparator<String> compare(EnumSet<CompareEnums> eops) {
        return (x,y) -> {
            if (eops.contains(CompareEnums.CASE_INSENSITIVE)){
                x = x.toLowerCase();
                y = y.toLowerCase();
            }
            if(eops.contains(CompareEnums.SPACE_INSENSITIVE)){
                x= x.replaceAll("\\s+","");
                y = y.replaceAll("\\s+","");
            }
            return eops.contains(CompareEnums.REVERSE) ? y.compareTo(x) : x.compareTo(y);
        };
    }
}
