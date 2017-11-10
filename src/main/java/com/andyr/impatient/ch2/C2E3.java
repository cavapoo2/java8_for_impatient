package com.andyr.impatient.ch2;

import com.andyr.impatient.Util;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;
//seems to make little difference, maybe jvm warmup is needed, or parallel is not much quicker
//for this operation
public class C2E3  implements Util {
    @Test
    @Override
    public void action() {
        Stream<String> strS = getWordsAsList("big.txt").stream();
        Stream<String> strP = getWordsAsList("big.txt").parallelStream();
        long single = measure(strS);
        long par = measure(strP);
        System.out.println("single=" + single + ",par=" + par);
    }


    public static long measure(Stream<String> stream){
        long start = System.currentTimeMillis();
        stream.filter(v -> v.length() > 12).count();
        return System.currentTimeMillis() - start;
    }

}
