/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.predium.utils;

/**
 *
 * @author MarquesV
 */
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class PlaceholderField {

    private JPasswordField passwordField;
    private JTextArea textArea;
    private boolean firstClickPassword = true; // Para controlar o primeiro clique no password field
    private boolean firstClickTextArea = true; // Para controlar o primeiro clique no text area

    public PlaceholderField(JPasswordField passwordField) {
        this.passwordField = passwordField;

        // Configura o placeholder para ser visível inicialmente
        passwordField.setEchoChar((char) 0); // Exibe o texto normal ao invés de asteriscos
        passwordField.setText("*****");

        // Adiciona o FocusListener
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (firstClickPassword) {
                    // Limpa o campo e define o echoChar para exibir asteriscos quando o usuário digitar
                    passwordField.setText("");
                    passwordField.setEchoChar('*');
                    firstClickPassword = false; // Define que o campo já foi clicado
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Caso o campo fique vazio após perder o foco, redefine o placeholder
                if (new String(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText("*****");
                    passwordField.setEchoChar((char) 0); // Mostra o texto normal
                    firstClickPassword = true; // Permite a redefinição no próximo clique
                }
            }
        });
    }

    public PlaceholderField(JPasswordField passwordField, String textoPlaceholder) {
        this.passwordField = passwordField;

        // Configura o placeholder para ser visível inicialmente
        passwordField.setEchoChar((char) 0); // Exibe o texto normal ao invés de asteriscos
        passwordField.setText(textoPlaceholder);

        // Adiciona o FocusListener
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (firstClickTextArea) {
                    // Limpa o campo e define o echoChar para exibir asteriscos quando o usuário digitar
                    passwordField.setText("");
                    passwordField.setEchoChar('*');
                    firstClickTextArea = false; // Define que o campo já foi clicado
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Caso o campo fique vazio após perder o foco, redefine o placeholder
                if (new String(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText("Digite sua senha");
                    passwordField.setEchoChar((char) 0); // Mostra o texto normal
                    firstClickTextArea = true; // Permite a redefinição no próximo clique
                }
            }
        });
    }
    // Construtor para JTextArea
    public PlaceholderField(JTextArea textArea, String textoPlaceholder) {
        this.textArea = textArea;

        // Configura o placeholder para ser visível inicialmente
        textArea.setText(textoPlaceholder);
        textArea.setForeground(java.awt.Color.GRAY); // Muda a cor do texto para cinza

        // Adiciona o FocusListener
        textArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (firstClickTextArea) {
                    // Limpa o campo quando o usuário clica
                    textArea.setText("");
                    textArea.setForeground(java.awt.Color.BLACK); // Muda a cor do texto para preto
                    firstClickTextArea = false; // Define que o campo já foi clicado
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Caso o campo fique vazio após perder o foco, redefine o placeholder
                if (textArea.getText().isEmpty()) {
                    textArea.setText(textoPlaceholder);
                    textArea.setForeground(java.awt.Color.GRAY); // Retorna a cor do texto para cinza
                    firstClickTextArea = true; // Permite a redefinição no próximo clique
                }
            }
        });
    }
    
}
