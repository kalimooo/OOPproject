package com.group23.app.Model;


public class GameClock {
    private long startTime;

    public GameClock() {
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
}
