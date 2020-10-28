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
    public void createNewGamePanel(int size, int winningResult, boolean versusAI, String difficulty, String[][] oldGameField, String oldPlayerSymbol) {
        if(size < 2 ) {
            JOptionPane.showMessageDialog(null, "Размер поля должен быть больше 2");
        }
        if (winningResult > size) {
            JOptionPane.showMessageDialog(null, "Количество выгрышных элеменов не может быть больше размера поля");
        } else  if (winningResult < 3) {
            JOptionPane.showMessageDialog(null, "Количество выгрышных элеменов должно быть больше 2");
        } else {
            if (this.gamePanel != null) {
                this.remove(Main.mainPanel.gamePanel);
            }
            GamePanel gamePanel = new GamePanel(size, winningResult, versusAI, difficulty, oldGameField, oldPlayerSymbol);
            this.gamePanel = gamePanel;
            this.add(gamePanel, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
            topBarPanel.winnerResult.setEnabled(false);
            topBarPanel.fieldSize.setEnabled(false);
            topBarPanel.endlessFieldCheckBox.setEnabled(false);
        }
    }
}
