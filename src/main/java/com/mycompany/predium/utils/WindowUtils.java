/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.predium.utils;

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
}