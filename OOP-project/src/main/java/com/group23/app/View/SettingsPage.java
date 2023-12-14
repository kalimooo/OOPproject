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
    Color menuBackgroundColor = Color.decode("#4f504e");
    Color buttonColor = Color.decode("#767675");
    private JButton exportButton;
    private JSlider volumeSlider;
    private JLabel volumeLabel; 
    public String sourceFilePath = "OOP-project\\src\\main\\java\\com\\group23\\app\\Settings\\highScore.txt";
    JLabel header;

    static SettingsPage settingsPage;

    private SettingsPage() {
        UIManager.put("OptionPane.background", menuBackgroundColor);
        UIManager.put("Panel.background", menuBackgroundColor);
        UIManager.put("OptionPane.messageFont", new Font(Font.SANS_SERIF, Font.BOLD, 18));
        UIManager.put("OptionPane.buttonFont", new Font(Font.SANS_SERIF, Font.BOLD, 18));
        UIManager.put("Button.background", buttonColor);
        UIManager.put("Button.foreground", Color.WHITE);

        setLayout(null);
        setBackground(Color.BLACK);

        header = new JLabel("Settings");
        header.setForeground(Color.WHITE);
        header.setFont(new Font(header.getFont().getName(), Font.PLAIN, 70));
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setVerticalAlignment(SwingConstants.TOP);
        header.setBounds(0, 30, SCREEN_WIDTH, SCREEN_HEIGHT -100);
        add(header);

        exportButton = new JButton("Export highscore to desktop");
        exportButton.setBounds(300, 300, 200, 30);
        exportButton.setFocusable(false);
        add(exportButton);

        volumeLabel = new JLabel("Music volume");
        volumeLabel.setForeground(Color.WHITE);
        volumeLabel.setBounds(300, 170, 200, 30);
        volumeLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(volumeLabel);

        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        volumeSlider.setBounds(300, 200, 200, 30);
        volumeSlider.setMajorTickSpacing(10);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);
        volumeSlider.setFocusable(false);

        add(volumeSlider);

        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleExportButtonClick();
            }
        });

        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                handleVolumeSliderChange();
            }
        });

        setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        setBackground(Color.BLACK);

        SettingsPage.settingsPage = this;
    }

    private void handleExportButtonClick() {
        copyFileToDesktop(sourceFilePath);

    }

    private void handleVolumeSliderChange() {
        GameWindow.getGameWindow().setBackgroundMusicVolume((double) volumeSlider.getValue()/1000);
    }

    public static SettingsPage getSettingsPage() {
        if (SettingsPage.settingsPage == null) {
            return new SettingsPage();
        }
        return SettingsPage.settingsPage;
    }

    public static void copyFileToDesktop(String sourceFilePath) {
        String desktopPath = System.getProperty("user.home") + "\\Desktop";

        try {
            Path sourcePath = Paths.get(sourceFilePath);
            Path destinationPath = Paths.get(desktopPath, sourcePath.getFileName().toString());

            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

            String successMessage = "<html><font color='white'> The highscore list is now exported to your desktop.\n </font></html>";
            JOptionPane.showMessageDialog(null, successMessage, "Export done", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to export highscore file.");
            JOptionPane.showMessageDialog(null, "Failed to export highscore file.", "Export Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
