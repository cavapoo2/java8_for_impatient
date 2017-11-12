package com.andyr.impatient.ch2;
import java.util.stream.Stream;

class Average{
    final long count;
    final Double sum;
    Average() {
        this.count=0;
        this.sum = 0.0;
    }
    Average(long c, Double v){
        this.count = c;
        this.sum = v;
    }
    Average accept(Double v) {
        return new Average(this.count + 1, this.sum + v);
    }
    Average combine(Average av) {
        return new Average(this.count + av.count, this.sum + av.sum);
    }
    double average() {
        return sum / count;
    }

}
public class C2E10 {

    public static void main(String [] args) {
        System.out.println("Average is " + C2E10.averageS(C2E10.getNewStream()));
    }

    static Stream<Double> getNewStream() {
        return Stream.of(1.1,2.2,3.3,4.4,5.5,6.6,7.7);
    }

    static double averageS(Stream<Double> in) {
        return in.reduce(new Average(),Average::accept,Average::combine).average();
    }
}

