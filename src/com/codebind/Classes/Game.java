package com.codebind.Classes;

public class Game {
    private String playerSymbol;
    public Game() {
        playerSymbol = "X";
    }
    public void switchPlayerSymbol() {
        if(playerSymbol == "X") playerSymbol = "O";
        else playerSymbol = "X";
    }
    public String getPlayerSymbol() {
        return  playerSymbol;
    }
}
