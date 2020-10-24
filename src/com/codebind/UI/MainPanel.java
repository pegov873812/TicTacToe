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
        createNewGamePanel(3, 3);
        topBarPanel = new TopBarPanel("3x3");
        this.topBarPanel = topBarPanel;
        this.add(topBarPanel, BorderLayout.NORTH);

    }
    public void createNewGamePanel(int size, int winningResult) {
        if (winningResult > size) {
            JOptionPane.showMessageDialog(null, "Количество выгрышных элеменов не может быть больше размера поля");
        } else {
            if (this.gamePanel != null) {
                this.remove(Main.mainPanel.gamePanel);
            }
            GamePanel gamePanel = new GamePanel(size, winningResult);
            this.gamePanel = gamePanel;
            this.add(gamePanel, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        }
    }
}
