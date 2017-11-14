package com.andyr.impatient.ch3;

import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class C3E1 {

    public static void main(String[] args) {
        Logger logger = Logger.getGlobal();
        logger.setLevel(Level.INFO);
        logIf(logger,() -> true,() -> "Hello World");
        logger.setLevel(Level.OFF);
        logIf(logger,() -> true,() -> "Hello World Again"); // not seen
        logger.setLevel(Level.INFO);
        int arr[] = {0,1,2,3,4,5,6,7,8,9,10};
        for(int i=0; i < arr.length; i++){
            final int v = i;
            logIf(logger,() -> v== 10, () -> "arr[10]=" + arr[10]);
        }

    }
    public static void logIf(Logger logger, Supplier<Boolean> cond, Supplier<String> message){
        if(logger.isLoggable(Level.INFO) && cond.get()){
            logger.log(logger.getLevel(),message.get());
        }
    }

}
