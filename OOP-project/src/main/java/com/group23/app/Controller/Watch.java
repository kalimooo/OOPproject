package com.group23.app.Controller;

import java.util.Scanner;

public class gameWatch {
    private long startTime;
    private long endTime;

    public gameWatch() {
        startTime = 0;
        endTime = 0;
    }
// for testing, should be removed further on
    public static void main(String[] args) {
        gameWatch watch11 = new gameWatch();
        watch11.startTimer();
        
        while (true) {
            Scanner sc = new Scanner(System.in); 
            String input = sc.nextLine();

            if (input.equals("time")) {
                watch11.stopTimer();
                watch11.getElapsedTime();
            }
        }
    }

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        endTime = System.nanoTime();
    }

    public void getElapsedTime() {
        long elapsedTime = (endTime - startTime) / 1_000_000_000; // To seconds
        long minutes = elapsedTime / 60;
        long hours = minutes / 60;
        
        String formattedTime = String.format("%02d:%02d:%02d", hours, minutes % 60, elapsedTime % 60);
        System.out.println("Elapsed time: " + formattedTime);
    }
}
