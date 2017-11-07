package com.andyr.impatient.ch1;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.util.Arrays;

//java -cp target/my_impatient-1.0-SNAPSHOT.jar com.andyr.impatient.ch1.C1E2 ~/java
public class C1E2 {
    public static void main(String [] args) {
        for (String s : args) {
            C1E2.getSubDirectorys(s);
            System.out.println("Using listfiles with method");
            File[] f = C1E2.getSubDirs(s);
            Arrays.asList(f).forEach(System.out::println);
            System.out.println("Using listfiles with lambda");
            File[] f2 = C1E2.getSubDirsM(s);
            Arrays.asList(f2).forEach(System.out::println);
        }
    }
    public static void getSubDirectorys(String dir) {
        try {
            Files.list(Paths.get(dir))
                    .filter(Files::isDirectory)
                    .forEach(System.out::println);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static File[] getSubDirs(String dir) {
        File d = new File(dir);
        return d.listFiles(File::isDirectory);

    }
    public static File[] getSubDirsM(String dir) {
        File d = new File(dir);
        return d.listFiles(v -> v.isDirectory());

    }
}