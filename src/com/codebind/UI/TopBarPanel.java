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

    public TopBarPanel(String size) {
        this.setLayout(new GridLayout());
        createFieldSizeComboBox(size);
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
                onComboBoxValueChanged();
            }
        });
    }
    /**
     * Функция вызывающаяся при имзменении размера игрового поля. Создает панель тренировки заново
     */
    private void onComboBoxValueChanged() {
        int size = Integer.parseInt(((String) fieldSizeComboBox.getSelectedItem()).split("x")[0]);
        Main.mainPanel.createNewGamePanel(size);
    }
}
