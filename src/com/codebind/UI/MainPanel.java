package com.codebind.UI;

import com.codebind.Main;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public GamePanel gamePanel;
    /**
     * Конструктор - создание нового объекта
     */
    public MainPanel() {
        createNewGamePanel(3);
    }
    public void createNewGamePanel(int size) {
        if(this.gamePanel != null) {
            this.remove(Main.mainPanel.gamePanel);
        }
        GamePanel gamePanel = new GamePanel(size);
        this.gamePanel = gamePanel;
        this.add(gamePanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
}
