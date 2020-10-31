package com.codebind.Classes;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;
/**
 * Класс для работы с файлами.
 * @autor Пегов
 * @version 1.0
 */
public class FileHelper {
    /**
     * Функция сохраняет игру в файл
     */
    public static void saveGameToFile(String gameString, String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(gameString);
            writer.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ошибка при сохранении игры");
        }
    }
    /**
     * Функция возвращает игру из файла преобразованную в строку
     * @return возвращает игру из файла преобразованную в строку
     */
    public static String loadGameFromFile(String fileName) {
        String data = new String();
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            data = myReader.next();
            myReader.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Ошибка при загрузке игры");
        }
        return data;
    }
}
