package com.codebind.UI;

import com.codebind.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
/**
 * Класс вывода верхней панели с инструментами со свойствами <b>fieldSizeComboBox</b>, <b>winnerResult</b>, <b>size</b>
 * @autor Пегов
 * @version 1.0
 */
public class TopBarPanel extends JPanel {
    /** Поле содержащее комбобокс для выбора размера поля */
    JComboBox fieldSizeComboBox;
    /** Поле содержащее поле ввода количество выгрышних элементов */
    JTextField winnerResult;
    /** Поле содержащее размер игрового поля */
    String size;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param size - размер игрового поля
     */
    public TopBarPanel(String size) {

        JButton startGameButton = new JButton("Начать новую игру");
        startGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String difficulty = null;
                String[] options = {"2 игрока", "Против компьютера"};
                int index = JOptionPane.showOptionDialog(null, "Выберите режим игры",
                        "Режим игры",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                if(index == 1) {
                    String[] difficultyOptions = {"Новичок", "Опытный", "Профессионал"};
                    int difficultyIndex = JOptionPane.showOptionDialog(null, "Выберите сложность",
                            "Сложность",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, difficultyOptions, difficultyOptions[0]);
                    difficulty = difficultyOptions[difficultyIndex];
                }
                startNewGame(index == 1, difficulty);
            }
        });
        this.add(startGameButton);
        createFieldSizeComboBox(size);
        this.size = size.split("x")[0];
        createWinnerResultPanel();
        JButton b = new JButton("Загрузить изображение");
        b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String[] options = {"Крестик", "Нолик"};
                int index = JOptionPane.showOptionDialog(null, "Какое изображение вы хотите загрузить",
                        "Загрузка изображения",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                String fileName = index == 0 ? "cross.png" : "circle.png";
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(b.getParent());
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        BufferedImage bi = ImageIO.read(selectedFile);
                        File outputfile = new File(fileName);
                        ImageIO.write(bi, "png", outputfile);
                    }
                    catch (Exception exception) {
                        JOptionPane.showMessageDialog(null,"Ошибка при загрузке изображения");
                    }
                }
            }
        });
        this.add(b);
    }
    /**
     * Функция создающая поле воода для количества выгрышних результатов
     */
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
     * Функция начинающее новую игру
     */
    private void startNewGame(boolean versusAI, String difficulty) {
        int size = 3;
        String comboBoxResult = (String) fieldSizeComboBox.getSelectedItem();
        if(comboBoxResult == "Бесконечное поле") size = 24;
        else size = Integer.parseInt(comboBoxResult.split("x")[0]);
        int winningResult = Integer.parseInt(winnerResult.getText());
        Main.mainPanel.createNewGamePanel(size, winningResult, versusAI, difficulty, null);
    }
}
