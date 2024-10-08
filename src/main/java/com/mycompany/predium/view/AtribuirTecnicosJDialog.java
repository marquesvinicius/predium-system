/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.mycompany.predium.view;

import com.mycompany.predium.controller.OrdemServicoController;
import com.mycompany.predium.controller.TecnicoController;
import com.mycompany.predium.model.OrdemServico;
import com.mycompany.predium.utils.TableUtils;
import com.mycompany.predium.utils.WindowUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MarquesV
 */
public class AtribuirTecnicosJDialog extends javax.swing.JDialog {

    private TecnicoController tecnicoController;
    private OrdemServicoController ordemController;
    private static OrdemServico ordem;

    /**
     * Creates new form AtribuirTecnicosJDialog
     */
    public AtribuirTecnicosJDialog(java.awt.Frame parent, boolean modal, OrdemServico ordem) {
        super(parent, modal);
        initComponents();
        this.ordem = ordem;
        // Debug: Imprimir detalhes da ordem
        System.out.println("Ordem recebida: ID = " + ordem.getId() + ", Descrição = " + ordem.getDescricao());
        preencherLabels(ordem);
        this.tecnicoController = new TecnicoController();
        this.ordemController = new OrdemServicoController();

        carregarTecnicosParaTabela();

        TableUtils.configureNonEditableTable(tecnicosjTable);
        WindowUtils.centralizarTela(this);
    }

    private void preencherLabels(OrdemServico ordem) {
        if (ordem != null) {
            ordemIDJLabel2.setText("Ordem Selecionada: " + ordem.getId());
            ordemDescricaoJLabel2.setText("Descrição: " + ordem.getDescricao());
            ordemStatusAtualJLabel2.setText("Status Atual: " + ordem.getStatus());
            ordemLocalJLabel2.setText("Local: " + ordem.getLocal());
        }
    }

    private void carregarTecnicosParaTabela() {
        List<String[]> tecnicos = tecnicoController.carregarTecnicos();

        // Define as colunas da tabela
        String[] colunas = {"ID", "Nome", "Especialidade"};

        // Cria o modelo da tabela
        DefaultTableModel tableModel = new DefaultTableModel(colunas, 0);

        // Adiciona cada técnico na tabela
        for (String[] tecnico : tecnicos) {
            tableModel.addRow(tecnico);
        }

        // Vincula o modelo à JTable
        tecnicosjTable.setModel(tableModel);
    }

    public void atualizarTabelaTecnicos() {
        DefaultTableModel model = (DefaultTableModel) tecnicosjTable.getModel();
        model.setRowCount(0); // Limpa todas as linhas atuais

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/db/tecnicos.csv"))) {
            String linha;
            boolean primeiraLinha = true; // Flag para ignorar a primeira linha (cabeçalho)
            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false; // Ignora o cabeçalho
                    continue;
                }
                String[] dados = linha.split(",");
                model.addRow(dados);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao ler o arquivo de técnicos.", "Erro", JOptionPane.ERROR_MESSAGE);
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tecnicosjTable = new javax.swing.JTable();
        ordemIDJLabel2 = new javax.swing.JLabel();
        ordemDescricaoJLabel2 = new javax.swing.JLabel();
        ordemStatusAtualJLabel2 = new javax.swing.JLabel();
        ordemLocalJLabel2 = new javax.swing.JLabel();
        instrucaoJLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        voltarJButton = new javax.swing.JButton();
        atribuirJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Atribuir Técnico");

        tecnicosjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "Especialidade"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tecnicosjTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tecnicosjTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tecnicosjTable.setShowHorizontalLines(true);
        jScrollPane1.setViewportView(tecnicosjTable);

        ordemIDJLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ordemIDJLabel2.setForeground(new java.awt.Color(0, 0, 0));
        ordemIDJLabel2.setText("Ordem Selecionada: {ID da ordem}");

        ordemDescricaoJLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ordemDescricaoJLabel2.setForeground(new java.awt.Color(0, 0, 0));
        ordemDescricaoJLabel2.setText("Descrição: {Descrição da Ordem}");

        ordemStatusAtualJLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ordemStatusAtualJLabel2.setForeground(new java.awt.Color(0, 0, 0));
        ordemStatusAtualJLabel2.setText("Status Atual: {Status da Ordem}");

        ordemLocalJLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ordemLocalJLabel2.setForeground(new java.awt.Color(0, 0, 0));
        ordemLocalJLabel2.setText("Local: {Local da Ordem}");

        instrucaoJLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        instrucaoJLabel.setForeground(new java.awt.Color(0, 0, 0));
        instrucaoJLabel.setText("Selecione um técnico da lista abaixo:");

        voltarJButton.setBackground(new java.awt.Color(187, 187, 187));
        voltarJButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        voltarJButton.setForeground(new java.awt.Color(0, 0, 0));
        voltarJButton.setText("Cancelar");
        voltarJButton.setFocusTraversalPolicyProvider(true);
        voltarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarJButtonActionPerformed(evt);
            }
        });

        atribuirJButton.setBackground(new java.awt.Color(30, 149, 115));
        atribuirJButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        atribuirJButton.setForeground(new java.awt.Color(255, 255, 255));
        atribuirJButton.setText("Atribuir Técnico");
        atribuirJButton.setFocusTraversalPolicyProvider(true);
        atribuirJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atribuirJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ordemIDJLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ordemDescricaoJLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ordemStatusAtualJLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ordemLocalJLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(instrucaoJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(voltarJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(atribuirJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 65, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {atribuirJButton, voltarJButton});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ordemIDJLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ordemDescricaoJLabel2)
                            .addComponent(ordemLocalJLabel2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ordemStatusAtualJLabel2)
                        .addGap(37, 37, 37)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(instrucaoJLabel)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(voltarJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(atribuirJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {atribuirJButton, voltarJButton});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void voltarJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarJButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_voltarJButtonActionPerformed

    private void atribuirJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atribuirJButtonActionPerformed
        int selectedRow = tecnicosjTable.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                // Obtém o ID do técnico como String e converte para Integer
                int tecnicoId = Integer.parseInt((String) tecnicosjTable.getValueAt(selectedRow, 0)); // ID do técnico selecionado

                // Verifica se a ordem é nula
                if (ordem != null) {
                    // Tenta atribuir o técnico à ordem e armazena o resultado
                    boolean sucesso = ordemController.atribuirTecnico(ordem.getId(), tecnicoId);

                    // Verifica se a atribuição foi bem-sucedida
                    if (sucesso) {
                        JOptionPane.showMessageDialog(this, "Técnico atribuído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose(); // Fecha o JDialog após a atribuição
                    } else {
                        JOptionPane.showMessageDialog(this, "Falha ao atribuir técnico. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Erro: Ordem não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erro ao converter ID do técnico.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecione um técnico.", "Atenção", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_atribuirJButtonActionPerformed

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
            java.util.logging.Logger.getLogger(AtribuirTecnicosJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AtribuirTecnicosJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AtribuirTecnicosJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AtribuirTecnicosJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AtribuirTecnicosJDialog dialog = new AtribuirTecnicosJDialog(new javax.swing.JFrame(), true, ordem);
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
    private javax.swing.JButton atribuirJButton;
    private javax.swing.JLabel instrucaoJLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel ordemDescricaoJLabel2;
    private javax.swing.JLabel ordemIDJLabel2;
    private javax.swing.JLabel ordemLocalJLabel2;
    private javax.swing.JLabel ordemStatusAtualJLabel2;
    private javax.swing.JTable tecnicosjTable;
    private javax.swing.JButton voltarJButton;
    // End of variables declaration//GEN-END:variables
}
