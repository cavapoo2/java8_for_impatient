package com.andyr.impatient.ch2;

import com.andyr.impatient.Util;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class C2E1 implements Util {
    public static void main(String [] args) {
        C2E1 obj = new C2E1();
        ArrayList<String> words = obj.getWordsAsArrayList("alice.txt");
        System.out.println(obj.countWordsWithThreads(words));

    }
    @Test
    @Override
    public void action() {
        ArrayList<String> words = getWordsAsArrayList("alice.txt");
        long count = countWordsWithThreads(words);
        long count2 = words.stream().filter(s -> s.length() > 12).count();
        long count3 = words.parallelStream().filter(s -> s.length() > 12).count();
        System.out.println(count);
        System.out.println(count2);
        System.out.println(count3);
        assertEquals(count,count2);
        assertEquals(count,count3);
    }
    public long countWordsWithThreads(ArrayList<String> words) {
        try {
            final int cores = Runtime.getRuntime().availableProcessors();
            int chunks = words.size() / cores;
            System.out.println("length = " + words.size() + ",cores=" + cores + ",chunks=" + chunks  );
            ExecutorService pool = Executors.newFixedThreadPool(cores);
            Set<Future<Long>> set = new HashSet<>();
            for(int i=0; i < cores; i++) {
                int start = i * chunks;
                int end =   (chunks) * (i+1);
                if (i == (cores - 1)) end = words.size();
                List<String> sub = words.subList(start,end);
                Callable<Long> callable = () -> {
                    long count =0;
                    for (String s : sub) {
                        if(s.length() > 12 ) count++;
                    }
                    return count;
                };
                Future<Long> fut = pool.submit(callable);
                set.add(fut);
            }
            long total =0;
            for(Future<Long> fut: set) {
                total += fut.get();
            }
            pool.shutdown();
            return total;

        } catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



}
