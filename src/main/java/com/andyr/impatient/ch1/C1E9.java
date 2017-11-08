package com.andyr.impatient.ch1;

import com.andyr.impatient.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

interface Collection2<T> extends Collection<T> {
    default void forEachIf(Consumer<T> action, Predicate<T> filter){
        forEach(e -> {
            if(filter.test(e)) {
                action.accept(e);
            }
        });
    }
}
class ArrayList2<T> extends ArrayList<T> implements Collection2<T> {

}

public class C1E9{
    public static void main(String[] args) {
        String [] names = {"John","mike","Tim","jack","mkl77frffkfporefp"};
        Collection2<String> c = new ArrayList2<>();
        for(String n: names) {
            c.add(n);
        }
        c.forEachIf(v -> message(v),v -> v.length()  < 10);
    }
    private static void message(String n) {
        System.out.println(n + " is too short a password, must be at least 10 characters long");

    }

}

