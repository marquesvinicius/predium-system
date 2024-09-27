/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.predium.utils;

/**
 *
 * @author MarquesV
 */
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;

public class KeyboardUtils {

    // Método estático para configurar o foco ao pressionar a tecla TAB
    public static void setTabFocus(JComponent component) {
        component.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    e.consume(); // Evita que a tabulação padrão ocorra
                    Component next = component.getFocusTraversalPolicy().getComponentAfter(component.getParent(), component);
                    if (next != null) {
                        next.requestFocus(); // Move o foco para o próximo componente
                    }
                }
            }
        });
    }
}
