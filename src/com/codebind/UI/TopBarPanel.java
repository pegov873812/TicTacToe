package com.codebind.UI;

import com.codebind.Classes.FileHelper;
import com.codebind.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
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
    public JTextField fieldSize;
    /** Поле содержащее поле ввода количество выгрышних элементов */
    public JTextField winnerResult;
    /** Поле содержащее размер игрового поля */
    String size;
    /** Поле содержащее выбо режима бесконечного поля */
    public JCheckBox endlessFieldCheckBox;
    public JButton saveGameButton;
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
        saveGameButton = new JButton("Сохранить игру");
        saveGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(saveGameButton.getParent());
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    FileHelper.saveGameToFile(Main.mainPanel.gamePanel.game.toString(), selectedFile.toString());
                }

            }
        });
        saveGameButton.setEnabled(false);
        this.add(saveGameButton);
        JButton loadGameButton = new JButton("Загрузить игру");
        loadGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(saveGameButton.getParent());
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String saveResult = FileHelper.loadGameFromFile(selectedFile.toString());
                    var arr = saveResult.split("\\|");
                    boolean endlessGame = Boolean.parseBoolean(arr[0]);
                    Main.mainPanel.topBarPanel.endlessFieldCheckBox.setSelected(endlessGame);
                    int saveSize = Integer.parseInt(arr[1]);
                    int saveWinningResult = Integer.parseInt(arr[3]);
                    String oldPlayerSymbol = arr[2].equals("X") ? "X" : "O";
                    boolean saveVersusAI = Boolean.parseBoolean(arr[4]);
                    String saveDifficulty = arr[5];
                    String[][] gameField = new String[saveSize][saveSize];
                    int xCounter = 0, yCounter = 0;
                    for (int i = 6; i < arr.length; i++) {
                        if (arr[i].equals("null") || arr[i].equals("")) {
                            gameField[yCounter][xCounter] = null;
                        } else {
                            gameField[yCounter][xCounter] = arr[i].equals("X") ? "X" : "O";
                        }
                        xCounter++;
                        if (xCounter == saveSize) {
                            yCounter++;
                            xCounter = 0;
                        }
                    }
                    Main.mainPanel.createNewGamePanel(saveSize, saveWinningResult, saveVersusAI, saveDifficulty, gameField, oldPlayerSymbol);
                }
            }
        });
        this.add(loadGameButton);
        this.add(startGameButton);
        createFieldSizeTextBox(size);
        this.size = size.split("x")[0];
        createWinnerResultPanel();
        createEndlessFieldCheckBox();
    }
    /**
     * Функция создающая поле воода для количества выгрышних результатов
     */
    public void createWinnerResultPanel() {
        JPanel panel1 = new JPanel();
        winnerResult = new JTextField(size, 5);
        panel1.add(new JLabel("Количество выигрышных элементов:"));
        panel1.add(winnerResult);
        this.add(panel1);
    }
    /**
     * Функция создающая чекбокс бесконечного поля
     */
    public void createEndlessFieldCheckBox() {
        JPanel panel1 = new JPanel();
        endlessFieldCheckBox = new JCheckBox();
        panel1.add(new JLabel("Бесконечное поле"));
        panel1.add(endlessFieldCheckBox);
        this.add(panel1);
    }
    /**
     * Функция создания нового комбо бокса для выбора размера игрового поля
     * @param size размер, который будет выбран по умолчанию
     */
    void createFieldSizeTextBox(String size) {
        ArrayList<String> items = new ArrayList<String>();
        fieldSize = new JTextField("3", 5);
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("Размер поля:"));
        panel1.add(fieldSize);
        this.add(panel1);
    }
    /**
     * Функция начинающее новую игру
     */
    private void startNewGame(boolean versusAI, String difficulty) {
        try {
            int size = Integer.parseInt(fieldSize.getText());
            int winningResult = Integer.parseInt(winnerResult.getText());
            Main.mainPanel.createNewGamePanel(size, winningResult, versusAI, difficulty, null, null);
        }
        catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Количество выгрышных элеменов и размер поля должны быть числом");
        }
    }
}
