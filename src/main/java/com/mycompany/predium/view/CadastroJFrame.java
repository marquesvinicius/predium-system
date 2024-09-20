/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.predium.view;

import com.mycompany.predium.controller.CadastroHandler;
import com.mycompany.predium.controller.LoginHandler;
import com.mycompany.predium.model.Usuario;
import javax.swing.JOptionPane;
import com.mycompany.predium.utils.WindowUtils;
import com.mycompany.predium.utils.PlaceholderField;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;

/**
 *
 * @author MarquesV
 */
public class CadastroJFrame extends javax.swing.JFrame {

    private LoginJFrame parentFrame;
    private CadastroHandler cadastroHandler;
    private LoginHandler loginHandler;

    /**
     * Creates new form CadastroJFrame
     */
    public CadastroJFrame(LoginJFrame parent, CadastroHandler cadastroHandler, LoginHandler loginHandler) {
        this.parentFrame = parent;
        this.cadastroHandler = cadastroHandler;
        this.loginHandler = loginHandler; // Armazena o LoginHandler
        initComponents();
        
        setTabFocus(usernameJTextArea);
        setTabFocus(jPasswordField);
        setTabFocus(confirmPasswordField);
        
        if (jPasswordField != null && confirmPasswordField != null) {
            new PlaceholderField(jPasswordField, "Digite sua senha");
            new PlaceholderField(confirmPasswordField, "Confirme sua senha");
        } else {
            throw new IllegalStateException("Campos de senha não inicializados!");
        }

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                if (parentFrame != null) {
                    parentFrame.setVisible(true);
                }
                dispose();
            }
        });

        WindowUtils.centralizarTela(this);
    }

    public CadastroJFrame() {
        initComponents();
        if (jPasswordField != null && confirmPasswordField != null) {
            new PlaceholderField(jPasswordField, "Digite sua senha");
            new PlaceholderField(confirmPasswordField, "Confirme sua senha");
        } else {
            throw new IllegalStateException("Campos de senha não inicializados!");
        }
        WindowUtils.centralizarTela(this);

    }

    private void setTabFocus(JComponent component) {
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainJPanel = new javax.swing.JPanel();
        loginJPanel = new javax.swing.JPanel();
        loginTitleJLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        usernameJLabel = new javax.swing.JLabel();
        senhaJLabel = new javax.swing.JLabel();
        registrarJButton = new javax.swing.JButton();
        jPasswordField = new javax.swing.JPasswordField();
        usernameJScrollPanel = new javax.swing.JScrollPane();
        usernameJTextArea = new javax.swing.JTextArea();
        javax.swing.JLabel confirmarSenhaJLabel = new javax.swing.JLabel();
        confirmPasswordField = new javax.swing.JPasswordField();
        imgLogoJLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro - Predium");
        setResizable(false);

        mainJPanel.setBackground(new java.awt.Color(245, 245, 245));

        loginJPanel.setBackground(new java.awt.Color(121, 203, 180));

        loginTitleJLabel.setBackground(new java.awt.Color(37, 57, 71));
        loginTitleJLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        loginTitleJLabel.setForeground(new java.awt.Color(37, 57, 71));
        loginTitleJLabel.setText("Cadastro");

        usernameJLabel.setBackground(new java.awt.Color(37, 57, 71));
        usernameJLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        usernameJLabel.setForeground(new java.awt.Color(37, 57, 71));
        usernameJLabel.setText("Username");

        senhaJLabel.setBackground(new java.awt.Color(37, 57, 71));
        senhaJLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        senhaJLabel.setForeground(new java.awt.Color(37, 57, 71));
        senhaJLabel.setText("Senha");

        registrarJButton.setBackground(new java.awt.Color(39, 57, 69));
        registrarJButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        registrarJButton.setForeground(new java.awt.Color(255, 255, 255));
        registrarJButton.setText("CADASTRAR");
        registrarJButton.setFocusTraversalPolicyProvider(true);
        registrarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarJButtonActionPerformed(evt);
            }
        });

        jPasswordField.setBackground(new java.awt.Color(255, 255, 255));
        jPasswordField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPasswordField.setForeground(new java.awt.Color(36, 60, 69));
        jPasswordField.setText("jPasswordField1");
        jPasswordField.setToolTipText("Insira sua senha");
        jPasswordField.setFocusTraversalPolicyProvider(true);
        jPasswordField.setNextFocusableComponent(confirmPasswordField);
        jPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldActionPerformed(evt);
            }
        });

        usernameJTextArea.setBackground(new java.awt.Color(255, 255, 255));
        usernameJTextArea.setColumns(20);
        usernameJTextArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        usernameJTextArea.setForeground(new java.awt.Color(36, 60, 69));
        usernameJTextArea.setRows(1);
        usernameJTextArea.setToolTipText("Insira seu username");
        usernameJTextArea.setFocusTraversalPolicyProvider(true);
        usernameJTextArea.setNextFocusableComponent(jPasswordField);
        usernameJScrollPanel.setViewportView(usernameJTextArea);

        confirmarSenhaJLabel.setBackground(new java.awt.Color(37, 57, 71));
        confirmarSenhaJLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        confirmarSenhaJLabel.setForeground(new java.awt.Color(37, 57, 71));
        confirmarSenhaJLabel.setText("Confirmar Senha");

        confirmPasswordField.setBackground(new java.awt.Color(255, 255, 255));
        confirmPasswordField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        confirmPasswordField.setForeground(new java.awt.Color(36, 60, 69));
        confirmPasswordField.setText("jPasswordField1");
        confirmPasswordField.setToolTipText("Confirme sua senha");
        confirmPasswordField.setFocusTraversalPolicyProvider(true);
        confirmPasswordField.setNextFocusableComponent(registrarJButton);
        confirmPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmPasswordFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout loginJPanelLayout = new javax.swing.GroupLayout(loginJPanel);
        loginJPanel.setLayout(loginJPanelLayout);
        loginJPanelLayout.setHorizontalGroup(
            loginJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginJPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(loginJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(senhaJLabel)
                    .addGroup(loginJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPasswordField)
                        .addComponent(loginTitleJLabel)
                        .addComponent(usernameJLabel)
                        .addComponent(jSeparator2)
                        .addComponent(registrarJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(usernameJScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                        .addComponent(confirmarSenhaJLabel)
                        .addComponent(confirmPasswordField)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        loginJPanelLayout.setVerticalGroup(
            loginJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginJPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(loginTitleJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(usernameJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(usernameJScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(senhaJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(confirmarSenhaJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(confirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(registrarJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        imgLogoJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgLogoJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/predium-logo+text.png"))); // NOI18N
        imgLogoJLabel.setText("jLabel6");

        javax.swing.GroupLayout mainJPanelLayout = new javax.swing.GroupLayout(mainJPanel);
        mainJPanel.setLayout(mainJPanelLayout);
        mainJPanelLayout.setHorizontalGroup(
            mainJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainJPanelLayout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addComponent(loginJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(imgLogoJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        mainJPanelLayout.setVerticalGroup(
            mainJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainJPanelLayout.createSequentialGroup()
                .addGroup(mainJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainJPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(loginJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainJPanelLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(imgLogoJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void registrarJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarJButtonActionPerformed
        String username = usernameJTextArea.getText().trim();
        String senha = new String(jPasswordField.getPassword());
        String confirmarSenha = new String(confirmPasswordField.getPassword());

        if (username.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!senha.equals(confirmarSenha)) {
            JOptionPane.showMessageDialog(this, "Senhas não conferem!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (cadastroHandler.usernameExiste(username)) {
            JOptionPane.showMessageDialog(this, "Usuário já existe!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        cadastroHandler.cadastrarUsuario(username, senha);
        loginHandler.adicionarUsuario(new Usuario(username, senha)); // Adiciona o novo usuário ao LoginHandler

        JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();

        if (parentFrame != null) {
            parentFrame.setVisible(true);
        }
    }//GEN-LAST:event_registrarJButtonActionPerformed

    private void confirmPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmPasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmPasswordFieldActionPerformed

    private void jPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField confirmPasswordField;
    private javax.swing.JLabel imgLogoJLabel;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel loginJPanel;
    private javax.swing.JLabel loginTitleJLabel;
    private javax.swing.JPanel mainJPanel;
    private javax.swing.JButton registrarJButton;
    private javax.swing.JLabel senhaJLabel;
    private javax.swing.JLabel usernameJLabel;
    private javax.swing.JScrollPane usernameJScrollPanel;
    private javax.swing.JTextArea usernameJTextArea;
    // End of variables declaration//GEN-END:variables
}
