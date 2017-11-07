package com.andyr.impatient.ch1;

import com.andyr.impatient.Util;
import org.junit.Test;

public class C1E6 implements Util {
    public static void main(String[] args) {
        C1E6 obj = new C1E6();
        obj.action();
    }
    @Test
    @Override
    public void action() {
        new Thread(uncheck( () ->
        {System.out.println("Zzz"); Thread.sleep(1000);}
        )).start();
    }
    public static Runnable uncheck(RunnableEx runner) {
        return () -> {
            try {
                runner.run();
            }catch(Exception i){}
        };
    }
    @FunctionalInterface
    interface RunnableEx {
        void run() throws Exception;
    }

}
