package com.andyr.impatient;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public interface Util {

    void action();

    default List<String> getWordsAsList(String name) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(name);
        String res = Util.getStringFromInputStream(is);
        return Arrays.asList(res.split("[\\P{L}]+"));
    }

    default String[] getWordsAsArray(String name) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(name);
        String res = Util.getStringFromInputStream(is);
        return res.split("[\\P{L}]+");
    }
    default String getFile(String name) {
        StringBuilder result = new StringBuilder("");
        //Get file from resources folder
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(name);
        return Util.getStringFromInputStream(is);
    }
    static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();

    }

}
