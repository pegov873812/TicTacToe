package com.codebind.UI;

import com.codebind.Classes.Game;
import com.codebind.Main;

import javax.swing.*;
import java.awt.*;
import java.io.File;


import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Класс панели в которой выводятся игровые кнопки с полями  <b>game</b> .
 * @autor Пегов
 * @version 1.0
 */
public class GamePanel extends JPanel {
    /** Поле в котрой содержится класс управляющий игрой */
    public Game game;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param size - размер игрового поля
     * @param winningResult - количество выгрышных элементов
     * @param versusAI - поле показывающее, что игра ведется против искусственного ителекта
     * @param difficulty - сложность искусственного интеллекта
     * @param oldGameField - старое состояние игрового поля
     */
    public GamePanel(int size, int winningResult, boolean versusAI, String difficulty, String[][] oldGameField, String oldGameSymbol) {
        game = new Game(size, winningResult, versusAI, difficulty, oldGameField, oldGameSymbol);
        setLayout(new java.awt.GridLayout(size, size));
        int xCounter = 0, yCounter = 0;
        for (int i = 1; i <= size * size; ++i) {
            JButton b = new JButton();
            b.setFont(new Font("Arial", Font.PLAIN, 50));

            if(oldGameField != null ) {
                if(oldGameField.length > xCounter && oldGameField[0].length > yCounter && oldGameField[xCounter][yCounter] != null && !oldGameField[xCounter][yCounter].equals("")){
                    setButtonIcon(b, oldGameField[xCounter][yCounter]);
                }
            }

            b.setName(xCounter + "/" + yCounter);
            xCounter++;
            if (xCounter == size) {
                xCounter = 0;
                yCounter++;
            }
            b.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (b.getIcon() == null) {
                        if (game.isGameOn()) {
                            makeMove(b);
                        }
                        if (game.isVersusAI() && game.isGameOn()) {
                            String name = game.makeAIMove();
                            var buttons = b.getParent().getComponents();
                            for (int i = 0; i < buttons.length; i++) {
                                if (buttons[i].getName().equals(name)) makeMove((JButton) buttons[i]);
                            }
                        }
                    }
                }
            });
            add(b);
        }
    }
    /**
     * Функция визуально отобраражающая ход
     * @param b кнопка в которую сделан ход
     */
    void makeMove(JButton b) {
        setButtonIcon(b, game.getPlayerSymbol());
        String name = b.getName();
        int x = Integer.parseInt(name.split("/")[0]);
        int y = Integer.parseInt(name.split("/")[1]);
        game.updateGameField(x, y);
        game.сheckEndGame();
        game.switchPlayerSymbol();
        String[][] oldGameField = game.getGameField();
        if(Main.mainPanel.topBarPanel.endlessFieldCheckBox.isSelected() && (oldGameField.length - 1 == x || oldGameField[0].length - 1 == y))
            Main.mainPanel.createNewGamePanel(oldGameField.length + 1, game.getWinningResult(), game.isVersusAI(), game.getDifficulty(), oldGameField, game.getPlayerSymbol());
    }
    /**
     * Функция устанавливает иконку у кнопки в завистимости от символа
     */
    void setButtonIcon(JButton b, String sybmol) {
        try {
            BufferedImage master;
            if (sybmol.equals("O"))
                master = ImageIO.read(new File("circle.png"));
            else {
                master = ImageIO.read(new File("cross.png"));
            }
            int height = b.getSize() == null || b.getSize().height == 0 ? (Main.mainPanel.getHeight() - 40) / game.getGameField().length : b.getSize().height;
            Image scaled = master.getScaledInstance(height, height, java.awt.Image.SCALE_SMOOTH);
            b.setIcon(new ImageIcon(scaled));

        }
        catch (Exception exception) {
            b.setText(game.getPlayerSymbol());
        }
    }
}
