package com.andyr.impatient.ch2;

import com.andyr.impatient.Util;
import javafx.util.Pair;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class C2E8 implements Util {
    @Test
    @Override
    public void action() {
        Stream si1 =  IntStream.range(1 ,10).boxed();
        Stream si2 = IntStream.range(11,20).boxed();
        Stream res = C2E8.zip(si1,si2);
        res.forEach(System.out::println) ;
        Stream si11 =  IntStream.range(1 ,10).boxed();
        Stream si22 = IntStream.range(11,20).boxed();
        Stream res2 = C2E8.zip2(si11,si22);
        res2.forEach(System.out::println) ;
        Stream si111 =  IntStream.range(1 ,10).boxed();
        Stream si222 = IntStream.range(11,20).boxed();
        Stream res22 = C2E8.zip3(si111,si222);
        res22.forEach(System.out::println) ;

    }
    static <T> Stream<Pair<T,T>> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> i2 = second.iterator();
        Iterator<T> i1 = first.iterator();
        List<Pair<T,T>> le = new ArrayList<>();
        while(i2.hasNext() && i1.hasNext())
        {
            le.add(new Pair<>(i1.next(),i2.next()));
        }
        return le.stream();
    }
    static <T> Stream<Pair<T,T>> zip3(Stream<T> first, Stream<T> second) {
        Iterator<T> i2 = second.iterator();
        Iterator<T> i1 = first.iterator();
        Stream.Builder<Pair<T,T>> builder = Stream.builder();
        while(i2.hasNext() && i1.hasNext())
        {
            builder.accept(new Pair<>(i1.next(),i2.next()));
        }
        return builder.build();
    }
    static public <T> Stream<T> zip2(Stream<T> first, Stream<T> second) {
        Iterator<T> secondAsIterator = second.iterator();
        Stream.Builder<T> builder = Stream.builder();
        first.forEach((e) -> {
            if (secondAsIterator.hasNext()) {
                builder.accept(e);
                builder.accept(secondAsIterator.next());
            } else {
                first.close();
            }
        });
        return builder.build();
    }

}
