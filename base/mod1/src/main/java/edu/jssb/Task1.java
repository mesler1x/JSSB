package edu.jssb;


public class Task1 {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0; i <= 1000; i++) {
            sum += i % 3 == 0 || i % 5 == 0 ? i : 0;
        }

        System.out.println(sum);
    }
}