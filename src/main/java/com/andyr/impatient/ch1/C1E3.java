package com.andyr.impatient.ch1;
import java.io.File;
import java.util.Arrays;

public class C1E3 {
    //input args are dir1 ext1 dir2 ex2 ...
    public static void main(String[] args) {
        if (args.length % 2 != 0) {
            System.out.println("must enter an even number of arguments");
            return;
        }

        for (int i = 0; i < args.length; i= i+2) {
            System.out.println("Showing for " + args[i] + " and " + args[i+1]);
            showFiles(args[i], args[i+1]);
        }

    }
    public static void showFiles(String dir,String ext) {

        File d = new File(dir);
        if (d.exists() && d.isDirectory()) {
            File[] r = d.listFiles(f -> f.getName().endsWith(ext));
            Arrays.asList(r).forEach(x -> System.out.println(x));
        }
        else{
            System.out.println("In String is not a directory");
        }

    }

}
