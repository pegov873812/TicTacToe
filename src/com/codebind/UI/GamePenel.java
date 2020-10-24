package com.codebind.UI;

import com.codebind.Classes.Game;
import com.codebind.Main;

import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class GamePenel extends JPanel {
    public Game game;
    public GamePenel(int size) {
        game = new Game(size,3 );
        setLayout(new java.awt.GridLayout(size, size));
        int xCounter = 0,yCounter = 0;
        for (int i = 1; i <= size * size; ++i) {
            JButton b = new JButton();
            b.setFont(new Font("Arial", Font.PLAIN, 50));
            b.setPreferredSize(new Dimension(100,100));
            b.setName(yCounter + "/" + xCounter);
            xCounter++;
            if(xCounter == size) {
                xCounter = 0;
                yCounter++;
            }
            b.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    b.setText(game.getPlayerSymbol());
                    String name = b.getName();
                    int x = Integer.parseInt(name.split("/")[0]);
                    int y = Integer.parseInt(name.split("/")[1]);
                    game.updateGameField(x,y);
                    game.CheckWinner();
                    game.switchPlayerSymbol();

                }
            });
            add(b);
        }
    }
}
