package com.codebind.UI;

import com.codebind.Main;

import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class GamePenel extends JPanel {
    public GamePenel(int size) {
        setLayout(new java.awt.GridLayout(size, size));
        for (int i = 1; i <= size * size; ++i) {
            JButton b = new JButton();
            b.setFont(new Font("Arial", Font.PLAIN, 50));
            b.setPreferredSize(new Dimension(100,100));
            b.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                }
            });
            add(b);
        }
    }
}
