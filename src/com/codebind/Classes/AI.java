package com.codebind.Classes;

import java.util.Random;
/**
 * 
 * @autor Игнатович
 * @version 1.0
 */
public class AI {
    private String difficulty;
    public AI(String difficulty){
        this.difficulty = difficulty;
    }

    public String makeMove(String[][] gameField, String playerSymbol, String aiSymbol) {
        switch (difficulty) {
            case "Новичок":
                return makeEasyMove(gameField);
            case "Опытный":
                return  makeMediumMove(gameField, playerSymbol, aiSymbol);
            case "Профессионал":
                return  makeHardMove(gameField, playerSymbol, aiSymbol);
            default:
                return makeEasyMove(gameField);
        }
    }
    public String makeEasyMove(String[][] gameField) {
        return  makeRandomMove(gameField);
    }
    public String makeMediumMove(String[][] gameField, String playerSymbol, String aiSymbol) {
        Random r = new Random();
        int randVal = r.nextInt((1000));
        if(randVal > 300) {
            return makeRandomMove(gameField);
        }
        else {
            return makeAlgoritmMove(gameField, playerSymbol, aiSymbol);
        }
    }
    public String makeHardMove(String[][] gameField, String playerSymbol, String aiSymbol) {
        return makeAlgoritmMove(gameField, playerSymbol, aiSymbol);
    }

    public String  makeRandomMove(String[][] gameField) {
        Random r = new Random();
        String result;
        while (true) {
            int x = r.nextInt((gameField.length));
            int y = r.nextInt((gameField[0].length));
            if(gameField[x][y] == null || gameField[x][y].isEmpty()) {
                result = x + "/" + y;
                break;
            }
        }
        return  result;
    }

    public String  makeAlgoritmMove(String[][] gameField, String playerSymbol, String aiSymbol) {
        boolean mademove = false;
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[0].length; j++) {
                gameField[i][j] = gameField[i][j] == null ? "" : gameField[i][j];
            }
        }
        for (int i = 0; i < 3; i++) {
            if (gameField[0][i].equals(gameField[1][i]) && gameField[0][i].equals(playerSymbol)) {
                if (gameField[2][i] != aiSymbol && gameField[2][i] != playerSymbol) {
                    mademove = true;
                    return "2/" + i;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (gameField[2][i].equals(gameField[1][i]) && gameField[2][i].equals(playerSymbol)) {
                if (gameField[0][i] != aiSymbol && gameField[0][i] != playerSymbol) {
                    mademove = true;
                    return "0/" + i;
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (gameField[i][0].equals(gameField[i][1]) && gameField[i][0].equals(playerSymbol)) {
                if (gameField[i][2] != aiSymbol && gameField[i][2] != playerSymbol) {
                    mademove = true;
                    return i + "/2";
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (gameField[i][2].equals(gameField[i][1]) && gameField[i][2].equals(playerSymbol)) {
                if (gameField[i][0] != aiSymbol && gameField[i][0] != playerSymbol) {
                    mademove = true;
                    return i + "/0";
                }
            }
        }
        if (gameField[0][0].equals(gameField[1][1]) && gameField[0][0].equals(playerSymbol)) {
            if (gameField[2][2] != aiSymbol && gameField[2][2] != playerSymbol) {
                mademove = true;
                return "2/2";
            }
        }
        if (gameField[2][2].equals(gameField[1][1]) && gameField[2][2].equals(playerSymbol)) {
            if (gameField[0][0] != aiSymbol && gameField[0][0] != playerSymbol) {
                mademove = true;
                return "0/0";
            }
        }
        if (gameField[0][0].equals(gameField[1][1]) && gameField[0][0].equals(playerSymbol)) {

            if (gameField[2][2] != aiSymbol && gameField[2][2] != playerSymbol) {
                mademove = true;
                return "2/2";
            }
        }
        if (gameField[0][2].equals(gameField[1][1]) && gameField[0][2].equals(playerSymbol)) {

            if (gameField[2][0] != aiSymbol && gameField[2][0] != playerSymbol) {
                mademove = true;
                return "2/0";
            }
        }
        if (gameField[2][0].equals(gameField[1][1]) && gameField[2][0].equals(playerSymbol)) {

            if (gameField[0][2] != aiSymbol && gameField[0][2] != playerSymbol) {
                mademove = true;
                return "0/2";
            }
        }
        for (int i = 0; i < 3; i++) {

            if (gameField[0][i].equals(gameField[1][i]) && gameField[0][i].equals(aiSymbol)) {
                if (gameField[2][i] != playerSymbol && gameField[2][i] != aiSymbol) {
                    mademove = true;
                    return "2/" + i;
                }
            }
        }
        for (int i = 0; i < 3; i++) {

            if (gameField[2][i].equals(gameField[1][i]) && gameField[0][i].equals(aiSymbol)) {
                if (gameField[0][i] != playerSymbol && gameField[0][i] != aiSymbol) {
                    mademove = true;
                    return "0/" + i;
                }
            }
        }
        for (int i = 0; i < 3; i++) {

            if (gameField[i][0].equals(gameField[i][1]) && gameField[i][0].equals(aiSymbol)) {
                if (gameField[i][2] != playerSymbol && gameField[i][2] != aiSymbol) {
                    mademove = true;
                    return i + "/2";
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            if (gameField[i][2].equals(gameField[i][1]) && gameField[i][2].equals(aiSymbol)) {
                if (gameField[i][0] != playerSymbol && gameField[i][0] != aiSymbol) {
                    mademove = true;
                    return i + "/0";
                }
            }
        }
        for (int i = 0; i < 3; i++) {

            if (gameField[2][i].equals(gameField[1][i]) && gameField[2][i].equals(aiSymbol)) {
                if (gameField[0][i] != playerSymbol && gameField[0][i] != aiSymbol) {
                    mademove = true;
                    return "0/" + i;
                }
            }
        }
        if (gameField[0][0].equals(gameField[1][1]) && gameField[0][0].equals(aiSymbol)) {
            if (gameField[2][2] != playerSymbol && gameField[2][2] != aiSymbol) {
                mademove = true;
                return "2/2";
            }
        }
        if (gameField[2][2].equals(gameField[1][1]) && gameField[2][2].equals(aiSymbol)) {

            if (gameField[0][0] != playerSymbol && gameField[0][0] != aiSymbol) {
                mademove = true;
                return "0/0";
            }
        }
        if (gameField[0][0].equals(gameField[1][1]) && gameField[0][0].equals(aiSymbol)) {
            if (gameField[2][2] != playerSymbol && gameField[2][2] != aiSymbol) {
                mademove = true;
                return "2/2";
            }
        }
        if (gameField[0][2].equals(gameField[1][1]) && gameField[0][2].equals(aiSymbol)) {

            if (gameField[2][0] != playerSymbol && gameField[2][0] != aiSymbol) {
                mademove = true;
                return "2/0";
            }
        }
        if (gameField[2][0].equals(gameField[1][1]) && gameField[2][0].equals(aiSymbol)) {

            if (gameField[0][2] != playerSymbol && gameField[0][2] != aiSymbol) {
                mademove = true;
                return "0/2";
            }
        }
        int rand1 = 0;
        int rand2 = 0;

        if(!mademove) {

            return  makeRandomMove(gameField);
        }
        return  null;
    }
}
