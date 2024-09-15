package edu.jssb;

public class Task2 {
    public static void main(String[] args) {
        int[][] x = { {20, 34, 2}, {9, 12, 18}, {3, 4, 5} };
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                min = Math.min(x[i][j], min);
            }
        }

        System.out.println(min);
    }
}
