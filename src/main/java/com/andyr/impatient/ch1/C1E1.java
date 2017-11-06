package com.andyr.impatient.ch1;
import com.andyr.impatient.Util;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArraySet;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class C1E1 implements Util {
    public static void main(String[] args)
    {
        C1E1 obj = new C1E1();
        String[] arr = obj.getWordsAsArray("alice.txt");
        final long currentThreadId = Thread.currentThread().getId();
        CopyOnWriteArraySet<Long> threadIds = new CopyOnWriteArraySet<>();
        Arrays.sort(arr,(a,b) -> {
            threadIds.add(Thread.currentThread().getId());
            return a.compareTo(b);
        });

        System.out.println("Number of threads " + threadIds.size());
        threadIds.clear();
        Arrays.parallelSort(arr,(a,b) -> {
            threadIds.add(Thread.currentThread().getId());
            return a.compareTo(b);
        });

        System.out.println("Number of threads " + threadIds.size());

    }
    @Test
    @Override
    public void action() {
        final long currentThreadId = Thread.currentThread().getId();
        String[] array = getWordsAsArray("alice.txt");
        //sort
        CopyOnWriteArraySet<Long> threadIds = new CopyOnWriteArraySet<>();
        Arrays.sort(array, (a, b) -> {
            threadIds.add(Thread.currentThread().getId());
            return a.compareTo(b);
        });
        assertEquals(1, threadIds.size());
        assertEquals(currentThreadId, threadIds.toArray()[0]);
        //parallelSort
        threadIds.clear();
        Arrays.parallelSort(array, (a, b) -> {
            threadIds.add(Thread.currentThread().getId());
            return a.compareTo(b);
        });
        assertTrue(threadIds.size() > 1);
    }
}
