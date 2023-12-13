package com.group23.app.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SettingsPage extends JPanel {
    static final int SCREEN_WIDTH = GameWindow.SCREEN_WIDTH;
    static final int SCREEN_HEIGHT = GameWindow.SCREEN_HEIGHT;

    private JButton exportButton;
    private JSlider volumeSlider;
    private JButton muteButton;
    public String sourceFilePath = "OOP-project\\src\\main\\java\\com\\group23\\app\\Settings\\highScore.txt";


    static SettingsPage settingsPage;

    private SettingsPage() {
        setLayout(null);
        setBackground(Color.BLACK);
        // Export Button
        exportButton = new JButton("Export highscore");
        exportButton.setBounds(50, 50, 200, 30);
        exportButton.setFocusable(false);
        add(exportButton);

        // Volume Slider
        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        volumeSlider.setBounds(50, 100, 200, 30);
        volumeSlider.setMajorTickSpacing(10);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);
        volumeSlider.setFocusable(false);
        add(volumeSlider);

        // Mute Button
        muteButton = new JButton("Mute");
        muteButton.setBounds(50, 150, 200, 30);
        muteButton.setFocusable(false);
        add(muteButton);

        // Add action listeners
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleExportButtonClick();
                copyFileToDesktop(sourceFilePath);
            }
        });

        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                handleVolumeSliderChange();
            }
        });

        muteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleMuteButtonClick();
            }
        });

        setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        setBackground(Color.BLACK);

        SettingsPage.settingsPage = this;
    }

    private void handleExportButtonClick() {
        // Add your export highscore logic here
        System.out.println("Export highscore button clicked");
    }

    private void handleVolumeSliderChange() {
        // Add your volume slider logic here
        int volumeValue = volumeSlider.getValue();
        System.out.println("Volume changed to: " + volumeValue);
    }

    private void handleMuteButtonClick() {
        // Add your mute/unmute logic here
        System.out.println("Mute button clicked");
    }

    public static SettingsPage getSettingsPage() {
        if (SettingsPage.settingsPage == null) {
            return new SettingsPage();
        }
        return SettingsPage.settingsPage;
    }

     public static void copyFileToDesktop(String sourceFilePath) {
        // Hämta användarens skrivbordsmapp
        String desktopPath = System.getProperty("user.home") + "\\Desktop";

        try {
            Path sourcePath = Paths.get(sourceFilePath);
            Path destinationPath = Paths.get(desktopPath, sourcePath.getFileName().toString());

            // Kopiera filen
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Filen har kopierats till skrivbordet: " + destinationPath.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Misslyckades med att kopiera filen.");
        }
    }

}
