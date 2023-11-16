package com.group23.app.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TitleField extends JPanel{
    
    static final int fieldWidth = GameWindow.SCREEN_WIDTH;
    static final int fieldHeight = GameWindow.SCREEN_HEIGHT;
    JLabel header;
    JLabel prompt;
    Image bgImg;

    static TitleField titleField;

    private TitleField() {
        super(null);

        header = new JLabel("Laserdome 2D");
        header.setForeground(Color.WHITE);
        header.setFont(new Font(header.getFont().getName(), Font.PLAIN, 30));
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setVerticalAlignment(SwingConstants.CENTER);
        header.setBounds(0, 0, fieldWidth, fieldHeight - 100);

        prompt = new JLabel("Press enter to start!");
        prompt.setForeground(Color.WHITE);
        prompt.setFont(new Font(prompt.getFont().getName(), Font.PLAIN, 20));
        prompt.setHorizontalAlignment(SwingConstants.CENTER);
        prompt.setVerticalAlignment(SwingConstants.CENTER);
        prompt.setBounds(0,200, fieldWidth, fieldHeight - 100);

        setBackground(Color.BLACK);
        setBounds(0, 0, fieldWidth, fieldHeight);
        add(header);
        add(prompt);

        this.bgImg = new ImageIcon("OOP-project/src/main/java/com/group23/app/View/Images/Images/WindowIcon.jpg").getImage();
        Dimension size = new Dimension(bgImg.getWidth(null), bgImg.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);

        TitleField.titleField = this;
    }

    public static TitleField getTitleField() {
        if (TitleField.titleField == null) {
            return new TitleField();
        }
        return TitleField.titleField;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImg, 0, 0, null);
    }
}
