package com.codebind.Classes;

import javax.swing.*;

public class Game {
    private String playerSymbol;
    private String[][] gameField;
    private  boolean gameOn;
    private int winningResult;
    private boolean versusAI;
    private AI ai;
    public Game(int size, int winningResult, boolean versusAI, String difficulty) {
        gameOn = true;
        playerSymbol = "X";
        gameField = new String[size][size];
        this.winningResult = winningResult;
        this.versusAI = versusAI;
        if(versusAI) ai = new AI(difficulty);

    }
    public void switchPlayerSymbol() {
        if(playerSymbol == "X") playerSymbol = "O";
        else playerSymbol = "X";
    }
    public String getPlayerSymbol() {
        return  playerSymbol;
    }
    public void updateGameField(int x, int y){
        gameField[x][y] = playerSymbol;
    }
    public  void CheckWinner() {
        if(isDeadHead()) {
            JOptionPane.showMessageDialog(null,"Ничья");
            gameOn = false;
            return;
        }
        if(CheckWinnerHorizontal() || CheckWinnerVertical() || CheckWinnerDiagonal()) {
            JOptionPane.showMessageDialog(null,"Победил  " + playerSymbol);
            gameOn = false;
        }
    }
    private boolean isDeadHead() {
        for(int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[0].length; j++) {
                if(gameField[i][j] == null || gameField[i][j].isEmpty())
                    return  false;
            }
        }
        return  true;
    }
    private boolean CheckWinnerHorizontal(){
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
    private boolean CheckWinnerVertical(){
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
    private boolean CheckWinnerDiagonal(){
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
    public boolean IsGameOn() {
        return  gameOn;
    }
    public boolean IsVersusAI() {return  versusAI;}
    public String MakeAIMove() {
        String humanSymbol = playerSymbol == "X" ? "O" : "X";
        return ai.makeMove(gameField, humanSymbol, playerSymbol);
    }
}
