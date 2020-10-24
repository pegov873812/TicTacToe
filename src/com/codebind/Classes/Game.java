package com.codebind.Classes;

import javax.swing.*;
import java.util.ArrayList;

public class Game {
    private String playerSymbol;
    private String[][] gameField;
    int winningResult;
    public Game(int size, int winningResult) {
        playerSymbol = "X";
        gameField = new String[size][size];
        this.winningResult = winningResult;

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
        if(CheckWinnerHorizontal()) {
            JOptionPane.showMessageDialog(null,"Победил  " + playerSymbol);
        }
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
}
