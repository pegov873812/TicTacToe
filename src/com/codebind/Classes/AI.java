package com.codebind.Classes;

import java.util.Random;

public class AI {
    public String MakeMove(String[][] gameField) {
        Random r = new Random();
        String result;
        while (true) {
            int x = r.nextInt((gameField.length - 1) + 1) + 0;
            int y = r.nextInt((gameField[0].length - 1) + 1) + 0;
            if(gameField[x][y] == null || gameField[x][y].isEmpty()) {
                result = x + "/" + y;
                break;
            }
        }
        return  result;
    }
}
