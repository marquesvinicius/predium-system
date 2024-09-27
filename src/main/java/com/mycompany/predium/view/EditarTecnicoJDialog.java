/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.predium.view;

import com.mycompany.predium.controller.TecnicoController;
import com.mycompany.predium.model.Tecnico;
import com.mycompany.predium.utils.KeyboardUtils;
import com.mycompany.predium.utils.WindowUtils;
import javax.swing.JOptionPane;

/**
 *
 * @author MarquesV
 */
public class EditarTecnicoJDialog extends javax.swing.JDialog {

    private static int tecnicoId;

    /**
     * Creates new form EditarTecnicoJDialog
     */
    public EditarTecnicoJDialog(java.awt.Frame parent, boolean modal, int tecnicoId) {
        super(parent, modal);
        initComponents();
        WindowUtils.centralizarTela(this);
        this.tecnicoId = tecnicoId;
        carregarDadosTecnico(tecnicoId);
        
        KeyboardUtils.setTabFocus(novoValorTextArea);
        WindowUtils.configurarEnterParaBotao(editarJButton);

    }

    public void carregarDadosTecnico(int id) {
        // Carregar dados do técnico a partir do CSV usando o ID
        TecnicoController controller = new TecnicoController();
        Tecnico tecnico = controller.buscarTecnicoPorId(id);

        if (tecnico != null) {
            tecnicoSelecionadoJLabel.setText("Técnico Selecionado: {ID: " + tecnico.getId() + "}");
            // Aqui poderíamos preencher o campo com o valor atual do técnico para edição, se necessário.
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        campoAEditarJLabel = new javax.swing.JLabel();
        novoValorJLabel = new javax.swing.JLabel();
        cancelarJButton = new javax.swing.JButton();
        editarJButton = new javax.swing.JButton();
        tituloJLabel = new javax.swing.JLabel();
        campoAEditarJComboBox = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        novoValorTextArea = new javax.swing.JTextArea();
        tecnicoSelecionadoJLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Técnico - Predium");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));
        jPanel1.setPreferredSize(new java.awt.Dimension(804, 720));

        campoAEditarJLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoAEditarJLabel.setForeground(new java.awt.Color(0, 0, 0));
        campoAEditarJLabel.setText("Campo:");

        novoValorJLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        novoValorJLabel.setForeground(new java.awt.Color(0, 0, 0));
        novoValorJLabel.setText("Novo Valor:");

        cancelarJButton.setBackground(new java.awt.Color(187, 187, 187));
        cancelarJButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cancelarJButton.setForeground(new java.awt.Color(0, 0, 0));
        cancelarJButton.setText("Cancelar");
        cancelarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarJButtonActionPerformed(evt);
            }
        });

        editarJButton.setBackground(new java.awt.Color(30, 149, 115));
        editarJButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        editarJButton.setForeground(new java.awt.Color(255, 255, 255));
        editarJButton.setText("Editar Técnico");
        editarJButton.setFocusTraversalPolicyProvider(true);
        editarJButton.setNextFocusableComponent(cancelarJButton);
        editarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarJButtonActionPerformed(evt);
            }
        });

        tituloJLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tituloJLabel.setForeground(new java.awt.Color(0, 0, 0));
        tituloJLabel.setText("Editar Técnico");

        campoAEditarJComboBox.setBackground(new java.awt.Color(255, 255, 255));
        campoAEditarJComboBox.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        campoAEditarJComboBox.setForeground(new java.awt.Color(0, 0, 0));
        campoAEditarJComboBox.setMaximumRowCount(2);
        campoAEditarJComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "Especialidade" }));
        campoAEditarJComboBox.setFocusTraversalPolicyProvider(true);
        campoAEditarJComboBox.setNextFocusableComponent(novoValorTextArea);
        campoAEditarJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoAEditarJComboBoxActionPerformed(evt);
            }
        });

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        novoValorTextArea.setBackground(new java.awt.Color(255, 255, 255));
        novoValorTextArea.setColumns(20);
        novoValorTextArea.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        novoValorTextArea.setRows(1);
        novoValorTextArea.setToolTipText("");
        novoValorTextArea.setFocusTraversalPolicyProvider(true);
        novoValorTextArea.setNextFocusableComponent(editarJButton);
        jScrollPane3.setViewportView(novoValorTextArea);

        tecnicoSelecionadoJLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tecnicoSelecionadoJLabel.setForeground(new java.awt.Color(0, 0, 0));
        tecnicoSelecionadoJLabel.setText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(99, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tituloJLabel)
                    .addComponent(tecnicoSelecionadoJLabel)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(campoAEditarJLabel)
                                .addComponent(campoAEditarJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(novoValorJLabel)))
                        .addComponent(editarJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelarJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(88, 88, 88))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(tituloJLabel)
                .addGap(19, 19, 19)
                .addComponent(tecnicoSelecionadoJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(campoAEditarJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoAEditarJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(novoValorJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addComponent(editarJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(cancelarJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 807, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarJButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cancelarJButtonActionPerformed

    private void editarJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarJButtonActionPerformed
        // Obtém o campo e o novo valor digitado
        String campoSelecionado = campoAEditarJComboBox.getSelectedItem().toString();
        String novoValor = novoValorTextArea.getText().trim();

        if (novoValor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O valor não pode estar vazio!");
            return;
        }

        TecnicoController controller = new TecnicoController();
        boolean sucesso = controller.editarTecnico(tecnicoId, campoSelecionado, novoValor);

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Técnico atualizado com sucesso!");
            this.dispose(); // Fecha a tela após sucesso
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar técnico.");
        }

    }//GEN-LAST:event_editarJButtonActionPerformed

    private void campoAEditarJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoAEditarJComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoAEditarJComboBoxActionPerformed

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
            java.util.logging.Logger.getLogger(EditarTecnicoJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarTecnicoJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarTecnicoJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarTecnicoJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditarTecnicoJDialog dialog = new EditarTecnicoJDialog(new javax.swing.JFrame(), true, tecnicoId);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> campoAEditarJComboBox;
    private javax.swing.JLabel campoAEditarJLabel;
    private javax.swing.JButton cancelarJButton;
    private javax.swing.JButton editarJButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel novoValorJLabel;
    private javax.swing.JTextArea novoValorTextArea;
    private javax.swing.JLabel tecnicoSelecionadoJLabel;
    private javax.swing.JLabel tituloJLabel;
    // End of variables declaration//GEN-END:variables
}
