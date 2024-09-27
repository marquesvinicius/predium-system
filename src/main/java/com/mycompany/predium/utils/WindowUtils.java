/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.predium.utils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author MarquesV
 */
public class WindowUtils {

    // Centraliza JFrame
    public static void centralizarTela(JFrame frame) {
        frame.setLocationRelativeTo(null);
    }

    // Centraliza JDialog
    public static void centralizarTela(JDialog dialog) {
        dialog.setLocationRelativeTo(null);
    }

    public static void configurarEnterParaBotao(JButton button) {
        button.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    button.doClick(); // Executa a ação do JButton
                }
            }
        });
    }
}
