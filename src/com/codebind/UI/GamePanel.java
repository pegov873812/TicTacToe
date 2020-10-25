package com.codebind.UI;

import com.codebind.Classes.Game;
import com.codebind.Main;

import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.ArrayList;


import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GamePanel extends JPanel {
    public Game game;

    public GamePanel(int size, int winningResult, boolean versusAI) {

        game = new Game(size, winningResult, versusAI);
        setLayout(new java.awt.GridLayout(size, size));
        int xCounter = 0, yCounter = 0;
        for (int i = 1; i <= size * size; ++i) {
            JButton b = new JButton();
            b.setFont(new Font("Arial", Font.PLAIN, 50));
            b.setPreferredSize(new Dimension(100, 100));
            b.setName(yCounter + "/" + xCounter);
            xCounter++;
            if (xCounter == size) {
                xCounter = 0;
                yCounter++;
            }
            b.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (game.IsGameOn() && b.getIcon() == null) {
                        makeMove(b);
                    }
                    if(game.IsVersusAI() && game.IsGameOn()) {
                        String name = game.MakeAIMove();
                        var buttons = b.getParent().getComponents();
                        for(int i = 0; i < buttons.length; i++) {
                            if(buttons[i].getName().equals(name)) makeMove((JButton) buttons[i]);
                        }
                    }
                }
            });
            add(b);
        }
    }
    void makeMove(JButton b) {
        try {
            BufferedImage master;
            if (game.getPlayerSymbol() == "O")
                master = ImageIO.read(new File("circle.png"));
            else {
                master = ImageIO.read(new File("cross.png"));
            }
            Image scaled = master.getScaledInstance(b.getSize().height, b.getSize().height, java.awt.Image.SCALE_SMOOTH);
            b.setIcon(new ImageIcon(scaled));

        }
        catch (Exception exception) {
            b.setText(game.getPlayerSymbol());
        }
        String name = b.getName();
        int x = Integer.parseInt(name.split("/")[0]);
        int y = Integer.parseInt(name.split("/")[1]);
        game.updateGameField(x, y);
        game.CheckWinner();
        game.switchPlayerSymbol();
    }
}
