package com.andyr.impatient.ch1;
import java.io.File;
import java.util.Arrays;

public class C1E4 {
    public static void main(String[] args) {
        for (String a: args) {
            System.out.println(">>>For " + a + "<<<<")
            C1E4.sortFiles(a);
        }
    }
    public static void sortFiles(String dir) {
        File d = new File(dir);
        if (d.exists() && d.isDirectory()) {
            File[] files = d.listFiles();
            Arrays.sort(files,(a,b) -> {
                if(a.isDirectory() && b.isFile()) return -1;
                else if(a.isFile() && b.isDirectory()) return 1;
                else return a.getName().toLowerCase().compareTo(b.getName().toLowerCase());
            });
            Arrays.asList(files).forEach(System.out::println);
        }
        else System.out.println("In String is not a directory");
    }
}
