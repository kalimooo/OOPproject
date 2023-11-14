package src.Controller;

import java.util.Scanner;

public class Watch {
    private long startTime;
    private long endTime;

    public Watch() {
        startTime = 0;
        endTime = 0;
    }
// for testing, should be removed further on
    public static void main(String[] args) {
        Watch watch11 = new Watch();
        watch11.startTimer();
        
        while (true) {
            Scanner sc = new Scanner(System.in); // System.in is a standard input stream
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
