package com.andyr.impatient.ch1;

import com.andyr.impatient.Util;
import org.junit.Test;

public class C1E7 implements Util {
    public static void main(String[] args) {
        C1E7 obj = new C1E7();
        obj.action();

    }
    public static Runnable andThen(RunnableEx r1, RunnableEx r2){
        return () -> {
            try {
                r1.run();
                r2.run();
            }catch(Exception ex) {
                ex.printStackTrace();
            }
        };
    }
    @Test
    @Override
    public void action() {
        new Thread(andThen(() ->  { System.out.println("Zzz");Thread.sleep(2000);}
                        , () -> { System.out.println("Yyy");Thread.sleep(1000);
                        })).start();
    }
    @FunctionalInterface
    interface RunnableEx {
        void run() throws Exception;
    }
}
