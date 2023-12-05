package com.group23.app.Model;


public class Timer {
    private long startTime;

    public Timer() {
        startTime = 0;
    }

    public void restartTimer() {
        startTime = System.nanoTime();
    }

    public long getElapsedTimeInSeconds() {
        long currentTime = System.nanoTime();
        long elapsedTime = (currentTime - startTime) / 1_000_000_000; // To seconds
        return elapsedTime;
    }

    /* // TODO Remove this method ?
    public void getElapsedTime() {
        long elapsedTime = (endTime - startTime) / 1_000_000_000; // To seconds
        long minutes = elapsedTime / 60;
        long hours = minutes / 60;
        
        //String formattedTime = String.format("%02d:%02d:%02d", hours, minutes % 60, elapsedTime % 60);
        //System.out.println("Elapsed time: " + formattedTime);
    }*/
}
