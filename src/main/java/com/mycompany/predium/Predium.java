/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.predium;

public class Predium {

    public static void main(String[] args) {
        CadastroHandler cadastroHandler = new CadastroHandler();
        LoginJFrame loginFrame = new LoginJFrame(cadastroHandler);
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Predium.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Cria e exibe a tela de login
        java.awt.EventQueue.invokeLater(() -> {
            new LoginJFrame(cadastroHandler).setVisible(true);
        });    }
}
