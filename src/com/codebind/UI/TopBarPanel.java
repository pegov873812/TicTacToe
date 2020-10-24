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
        winnerResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSettigsValueChanged();
            }
        });
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
        fieldSizeComboBox = new JComboBox(items.toArray());
        fieldSizeComboBox.setSelectedItem(size);
        this.add(fieldSizeComboBox);
        fieldSizeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSettigsValueChanged();
            }
        });
    }
    /**
     * Функция вызывающаяся при имзменении размера игрового поля. Создает панель тренировки заново
     */
    private void onSettigsValueChanged() {
        int size = Integer.parseInt(((String) fieldSizeComboBox.getSelectedItem()).split("x")[0]);
        int winningResult = Integer.parseInt(winnerResult.getText());
        Main.mainPanel.createNewGamePanel(size, winningResult);
    }
}
