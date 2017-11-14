package com.andyr.impatient.ch3;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class C3E2 {
    public static void main(String[] args) {
        C3E2 obj = new C3E2();
        obj.withLock(new ReentrantLock(), () -> {
            Random r = new Random();
            double rv = 10 + (20  - 10) * r.nextDouble();
            System.out.println("Value is " + rv);
        });

    }
    public void withLock(ReentrantLock lock, Runnable action) {
        lock.lock();
        try {
            action.run();
        }finally {
            lock.unlock();
        }
    }
}
