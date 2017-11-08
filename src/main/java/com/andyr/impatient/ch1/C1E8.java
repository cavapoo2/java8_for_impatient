package com.andyr.impatient.ch1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class C1E8 {
    public static void main(String [] args) {
        String[] names = {"Peter","Paul","Mary"};
        List<Runnable> runners = C1E8.getRunners(names);
        runners.forEach(r -> {
            new Thread(r).start();
        });

    }
    public static List<Runnable> getRunners(String[] names) {
        List<Runnable> runners = new ArrayList<>();
        for(String name: names){
            runners.add(() -> System.out.println(name));
        }
        return runners;
    }
    /* Won't compile
    public static List<Runnable> getRunners2(String[] names) {
        List<Runnable> runners = new ArrayList<>();
        for(int i=0; i < names.length; i++){
            runners.add(() -> System.out.println(names[i]));
        }
        return runners;
    }*/


}
