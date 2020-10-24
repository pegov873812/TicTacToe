package com.codebind.UI;

import com.codebind.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * Класс вывода верхней панели с инструментами со свойствами <b>currentSymbolText</b>, <b>stopWatchPane</b>, <b>fieldSizeComboBox</b>, <b>symbolTypeComboBox</b>
 * @autor Цветкова
 * @version 1.0
 */
public class TopBarPanel extends JPanel {
    JComboBox fieldSizeComboBox;
    JTextField winnerResult;
    String size;

    public TopBarPanel(String size) {
        JButton startGameButton = new JButton("Начать новую игру");
        startGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                startNewGame();
            }
        });
        this.add(startGameButton);
        this.setLayout(new GridLayout());
        createFieldSizeComboBox(size);
        this.size = size.split("x")[0];
        createWinnerResultPanel();
    }
    public void createWinnerResultPanel() {
        JPanel panel1 = new JPanel();
        winnerResult = new JTextField(size, 10);
        panel1.add(new JLabel("Количество выгрыщних элементов:"));
        panel1.add(winnerResult);
        this.add(panel1);
    }
    /**
     * Функция создания нового комбо бокса для выбора размера игрового поля
     * @param size размер, который будет выбран по умолчанию
     */
    void createFieldSizeComboBox(String size) {
        ArrayList<String> items = new ArrayList<String>();
        items.add("3x3");
        items.add("5x5");
        items.add("7x7");
        items.add("9x9");
        items.add("Бесконечное поле");
        fieldSizeComboBox = new JComboBox(items.toArray());
        fieldSizeComboBox.setSelectedItem(size);
        this.add(new JLabel("Размер поля:"));
        this.add(fieldSizeComboBox);
    }
    /**
     * Функция вызывающаяся при имзменении размера игрового поля. Создает панель тренировки заново
     */
    private void startNewGame() {
        int size = 3;
        String comboBoxResult = (String) fieldSizeComboBox.getSelectedItem();
        if(comboBoxResult == "Бесконечное поле") size = 24;
        else Integer.parseInt(comboBoxResult.split("x")[0]);
        int winningResult = Integer.parseInt(winnerResult.getText());
        Main.mainPanel.createNewGamePanel(size, winningResult);
    }
}
