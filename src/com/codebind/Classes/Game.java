package com.codebind.Classes;

import com.codebind.Main;
import javax.swing.*;
/**
 * Класс управления игрой с полями <b>playerSymbol</b>, <b>gameField</b>,<b>gameOn</b>,<b>winningResult</b>,<b>versusAI</b>,<b>ai</b>.
 * @autor Пегов
 * @version 1.0
 */
public class Game {
    /** Поле символа которым ходит игрок */
    private String playerSymbol;
    /** Поле игрового поля */
    private String[][] gameField;
    /** Поле показывающее, что игра не завершена */
    private  boolean gameOn;
    /** Поле количество выгрышных результатов */
    private int winningResult;
    /** Поле показывающее, что игра идет против искусственного интеллекта  */
    private boolean versusAI;
    /** Поле искусственного интеллекта  */
    private AI ai;
    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param size - размер игрового поля
     * @param winningResult - количество выгрышных результатов
     * @param versusAI - поле показывающее, что игра идет против искусственного интеллекта
     * @param difficulty - сложность искусственного интеллекта
     * @param oldPlayeSymbol - символ предыдущего игрока
     */
    public Game(int size, int winningResult, boolean versusAI, String difficulty, String[][] oldGamePanel, String oldPlayeSymbol) {
        gameOn = true;
        playerSymbol = oldPlayeSymbol == null ?  "X" : oldPlayeSymbol;
        gameField = new String[size][size];
        if(oldGamePanel != null) {
            //playerSymbol = "O";
            for(int i = 0; i < oldGamePanel.length; i++) {
                for(int j = 0; j < oldGamePanel[0].length; j++) {
                    gameField[i][j] = oldGamePanel[i][j];
                }
            }
        }
        this.winningResult = winningResult;
        this.versusAI = versusAI;
        if(versusAI) ai = new AI(difficulty);
    }
    /**
     * Функция переключающая игроков
     */
    public void switchPlayerSymbol() {
        if(playerSymbol.equals("X")) playerSymbol = "O";
        else playerSymbol = "X";
    }
    /**
     * Функция возвращает символ которым ходит текущий игрок
     * @return возвращает символ которым ходит текущий игрок
     */
    public String getPlayerSymbol() {
        return  playerSymbol;
    }
    /**
     * Функция обновляет игровое поле и ставит символ текущего игрока в  указанные координаты
     */
    public void updateGameField(int x, int y){
        gameField[x][y] = playerSymbol;
    }
    /**
     * Функция проверяет конец игры и завершает ее
     */
    public void сheckEndGame() {
        if(checkWinnerHorizontal() || сheckWinnerVertical() || сheckWinnerDiagonal()) {
            JOptionPane.showMessageDialog(null,"Победил  " + playerSymbol);
            gameOn = false;
            Main.mainPanel.topBarPanel.winnerResult.setEnabled(true);
            Main.mainPanel.topBarPanel.fieldSize.setEnabled(true);
            Main.mainPanel.topBarPanel.endlessFieldCheckBox.setEnabled(true);
            Main.mainPanel.topBarPanel.saveGameButton.setEnabled(false);
            return;
        }
        if(isDeadHead()) {
            JOptionPane.showMessageDialog(null,"Ничья");
            gameOn = false;
            Main.mainPanel.topBarPanel.winnerResult.setEnabled(true);
            Main.mainPanel.topBarPanel.fieldSize.setEnabled(true);
            Main.mainPanel.topBarPanel.endlessFieldCheckBox.setEnabled(true);
            Main.mainPanel.topBarPanel.saveGameButton.setEnabled(false);
        }
    }
    /**
     * Функция проверяет на ничью
     * @return возвращает true если все поле занято
     */
    private boolean isDeadHead() {
        for(int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[0].length; j++) {
                if(gameField[i][j] == null || gameField[i][j].isEmpty())
                    return  false;
            }
        }
        return  true;
    }
    /**
     * Функция проверяет конец игры по горизонтали
     * @return возвращает true если найдено winningResult символов playerSymbol по горизонтали
     */
    private boolean checkWinnerHorizontal(){
        int counter = 0;
        for(int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[0].length; j++) {
                if(gameField[i][j] == playerSymbol) counter++;
                else  counter = 0;
                if(counter == winningResult) return true;
            }
            counter = 0;
        }
        return  false;
    }
    /**
     * Функция проверяет конец игры по горизонтали
     * @return возвращает true если найдено winningResult символов playerSymbol по вертикали
     */
    private boolean сheckWinnerVertical(){
        int counter = 0;
        for(int i = 0; i < gameField[0].length; i++) {
            for (int j = 0; j < gameField.length; j++) {
                if(gameField[j][i] == playerSymbol) counter++;
                else  counter = 0;
                if(counter == winningResult) return true;
            }
            counter = 0;
        }
        return  false;
    }
    /**
     * Функция проверяет конец игры по горизонтали
     * @return возвращает true если найдено winningResult символов playerSymbol по диагонали
     */
    private boolean сheckWinnerDiagonal(){
        int counter = 0;
        for(int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[0].length; j++) {
                if(gameField[i][j] == playerSymbol) {
                    counter++;
                    for (int z = 1; z < gameField.length; z++) {
                        if(i + z < gameField.length && j + z < gameField[0].length) {
                            if(gameField[i + z][j + z] == playerSymbol) {
                                counter++;
                                if(counter == winningResult) return true;
                            }
                            else  counter = 0;
                        }
                    }
                    counter = 1;
                    for (int z = 1; z < gameField.length; z++) {
                        if(i + z < gameField.length && j - z >= 0) {
                            if(gameField[i + z][j - z] == playerSymbol) {
                                counter++;
                                if(counter == winningResult) return true;
                            }
                            else  counter = 0;
                        }
                    }
                }
                counter = 0;
            }
        }
        return  false;
    }
    /**
     * Функция возвращает статус игры
     * @return возвращает статус игры
     */
    public boolean isGameOn() {
        return  gameOn;
    }
    /**
     * Функция возвращает ведется ли игра против искусственного интеллекта
     * @return возвращает ведется ли игра против искусственного интеллекта
     */
    public boolean isVersusAI() {return  versusAI;}
    /**
     * Функция делает ход искусственного интеллекта
     * @return возвращает ячейку в которую в которую походил искусственный интеллект
     */
    public String makeAIMove() {
        String humanSymbol = playerSymbol.equals("X") ? "O" : "X";
        return ai.makeMove(gameField, humanSymbol, playerSymbol);
    }
    /**
     * Функция возвращает сложность искусственного инелекта
     * @return возвращает сложность искусственного инелекта
     */
    public String getDifficulty() {
        if(ai != null)
            return  ai.getDifficulty();
        return null;
    }
    /**
     * Функция возвращает игровое поле
     * @return возвращает игровое поле
     */
    public String[][] getGameField() {
        return  gameField;
    }
    /**
     * Функция возвращает количество выгрышных решений
     * @return возвращает количество выгрышных решений
     */
    public int getWinningResult() {
        return winningResult;
    }
    /**
     * Функция возвращает преобразованный в строку экземплр класса
     * @return возвращает преобразованный в строку экземплр класса
     */
    public String toString(){
        String result = gameField.length + "|" + playerSymbol + "|" + winningResult + "|" + versusAI + "|";
        if(versusAI) {
            result += ai.getDifficulty();
        }
        else  {
            result += "false";
        }
        for(int i = 0; i < gameField.length; i++) {
            for(int j = 0; j < gameField.length; j++) {
                result += "|" + gameField[i][j];
            }
        }
        return result;
    }

}
