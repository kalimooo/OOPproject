package com.group23.app.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TitleField extends JPanel{
    
    static final int fieldWidth = GameWindow.SCREEN_WIDTH;
    static final int fieldHeight = GameWindow.SCREEN_HEIGHT;
    JLabel header;
    JLabel promptEnter;
    JLabel promptT;
    Image bgImg;

    static TitleField titleField;

    private TitleField() {
        super(null);

        header = new JLabel("Laserdome 2D");
        header.setForeground(Color.WHITE);
        header.setFont(new Font(header.getFont().getName(), Font.PLAIN, 70));
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setVerticalAlignment(SwingConstants.TOP);
        header.setBounds(0, 100, fieldWidth, fieldHeight -100);

        // Create a new JLabel with the text "Press enter to start!"
        promptEnter = new JLabel("Press enter to start!");

        // Set the foreground color of the label to white
        promptEnter.setForeground(Color.WHITE);

        // Set the font of the label to the current font name, plain style, and size 20
        promptEnter.setFont(new Font(promptEnter.getFont().getName(), Font.PLAIN, 40));

        // Set the horizontal alignment of the label to center
        promptEnter.setHorizontalAlignment(SwingConstants.CENTER);

        // Set the vertical alignment of the label to center
        promptEnter.setVerticalAlignment(SwingConstants.CENTER);

        // Set the bounds of the label. The x and y coordinates are 0 and 200 respectively,
        // and the width and height are fieldWidth and fieldHeight - 100 respectively
        promptEnter.setBounds(0,220, fieldWidth, fieldHeight - 100);
        

        promptT = new JLabel("Press T to to enter the tutorial!");
        promptT.setForeground(Color.WHITE);
        promptT.setFont(new Font(promptT.getFont().getName(), Font.PLAIN, 40));
        promptT.setHorizontalAlignment(SwingConstants.CENTER);
        promptT.setVerticalAlignment(SwingConstants.CENTER);
        promptT.setBounds(0,300, fieldWidth, fieldHeight - 100);

        setBackground(Color.BLACK);
        setBounds(0, 0, fieldWidth, fieldHeight);
        add(header);
        add(promptEnter);
        add(promptT);

        this.bgImg = GameWindow.loadScaledImage("OOP-project/src/main/java/com/group23/app/View/Images/Images/AlternativeBG2.jpg", fieldWidth, fieldWidth).getImage();
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
