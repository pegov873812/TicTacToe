package com.codebind.UI;

import com.codebind.Main;

import javax.swing.*;
import java.awt.*;
/**
 * Класс отображающий интерфейс приложения с полями <b>gamePanel</b> и  <b>topBarPanel</b>.
 * @autor Пегов
 * @version 1.0
 */
public class MainPanel extends JPanel {
    public JPanel GamePanelContainer;
    /** Поле содержащее игровую панель */
    public GamePanel gamePanel;
    /** Поле содержащее панель с кнпками интерфейса */
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
    /**
     * Функция создающая новую игровую панель
     * @param size размер игрового поля
     * @param winningResult количество выхрышных элементов
     * @param versusAI поле показывающее, что игра ведется против искусственного ителекта
     * @param difficulty сложность искусственного интеллекта
     * @param oldGameField предыдущее состояние игрового поля
     */
    public void createNewGamePanel(int size, int winningResult, boolean versusAI, String difficulty, String[][] oldGameField) {
        if (winningResult > size) {
            JOptionPane.showMessageDialog(null, "Количество выгрышных элеменов не может быть больше размера поля");
        } else {
            if (this.GamePanelContainer != null && this.gamePanel != null) {
                this.remove(Main.mainPanel.GamePanelContainer);
            }
            GamePanelContainer = new JPanel();
            ScrollPane scrollPane = new ScrollPane();
            GamePanel gamePanel = new GamePanel(size, winningResult, versusAI, difficulty, oldGameField);
            scrollPane.add(gamePanel);
            scrollPane.setSize(Main.mainPanel.getSize());
            GamePanelContainer.add(scrollPane);
            this.gamePanel = gamePanel;
            this.add(GamePanelContainer, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        }
    }
}
