package main.com.ianpert;

import java.util.Random;

public class TestRandom {

    public static void main(String args[]) {

        Random rnd = new Random();

        for (int i = 0; i < 50; i++) {
            System.out.println(rnd.nextInt(1, 100));
        }
    }
    
}
