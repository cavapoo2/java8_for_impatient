package com.andyr.impatient.ch3;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;

import static junit.framework.Assert.assertEquals;

public class C3E9 {
    class Names {
        private String firstname;
        private String lastname;
        Names(String first,String last) {
            this.firstname = first;
            this.lastname = last;
        }
    }
    public static void main(String [] args) {
        C3E9 obj = new C3E9();
        obj.action();
    }
    @Test
    public void action() {
        Names[] names = {
                new Names("John", "Smith"),
                new Names("Peter", "Pan"),
                new Names("Led", "Zeppelin"),
                new Names("Pele",null),
                new Names(null,"Zico"),
                new Names("Fred","Zzz")
        };
        Arrays.sort(names,lexographicComp("lastname","firstname"));
        System.out.println("Sorted by lastname");
        assertEquals("Pele",names[0].firstname);
        Arrays.asList(names).forEach(x -> System.out.println("first=" + x.firstname + ",last=" + x.lastname));
        Arrays.sort(names,lexographicComp("firstname","lastname"));
        assertEquals("Zico",names[0].lastname);
        System.out.println("Sorted by firstname");
        Arrays.asList(names).forEach(x -> System.out.println("first=" + x.firstname + ",last=" + x.lastname));

    }
    public <T> Comparator<T> lexographicComp(String... names) {
            return (x,y) -> {
                for (String name : names) {
                    try {
                        Field field = x.getClass().getDeclaredField(name);
                        field.setAccessible(true);
                        Object valX = field.get(x);
                        Object valY = field.get(y);
                        if(valX == null && valY == null) continue;
                        else if(valX != null && valY == null) return 1;
                        else if(valX == null && valY != null) return -1;
                        else return field.get(x).toString().compareTo(field.get(y).toString());

                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                return 0;
            };
    }
}
