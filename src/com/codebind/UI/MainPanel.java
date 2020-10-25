package com.codebind.UI;

import com.codebind.Main;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public GamePanel gamePanel;
    public TopBarPanel topBarPanel;
    /**
     * Конструктор - создание нового объекта
     */
    public MainPanel() {
        this.setLayout(new BorderLayout());
        topBarPanel = new TopBarPanel("3x3");
        this.topBarPanel = topBarPanel;
        this.add(topBarPanel, BorderLayout.NORTH);

    }
    public void createNewGamePanel(int size, int winningResult, boolean versusAI, String difficulty) {
        if (winningResult > size) {
            JOptionPane.showMessageDialog(null, "Количество выгрышных элеменов не может быть больше размера поля");
        } else {
            if (this.gamePanel != null) {
                this.remove(Main.mainPanel.gamePanel);
            }
            GamePanel gamePanel = new GamePanel(size, winningResult, versusAI, difficulty);
            this.gamePanel = gamePanel;
            this.add(gamePanel, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        }
    }
}
