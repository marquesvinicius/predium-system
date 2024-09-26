/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.predium.view;

import com.mycompany.predium.FileWatcher;
import com.mycompany.predium.controller.LoginHandler;
import com.mycompany.predium.controller.OrdemServicoController;
import com.mycompany.predium.utils.TableUtils;
import com.mycompany.predium.utils.WindowUtils;

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MarquesV
 */
public class PrincipalJFrame extends javax.swing.JFrame {

    private LoginHandler loginHandler;
    private OrdemServicoController ordemController;

    /**
     * Creates new form PrincipalJFrame
     */
    public PrincipalJFrame(String nomeDoUsuario, LoginHandler loginHandler) {
        initComponents();
        WindowUtils.centralizarTela(this);
        olaUserJLabel.setText("Olá " + nomeDoUsuario + "!");
        this.loginHandler = loginHandler;
        this.ordemController = new OrdemServicoController();
        carregarOrdensParaTabela();
        atualizarTabela();

        Path path = Paths.get("src/main/resources/db");
        new FileWatcher(path, this, loginHandler).start(); // Inicia o monitoramento do arquivo de ordens
        TableUtils.configureNonEditableTable(ordensjTable);
    }

    public PrincipalJFrame() {
        initComponents();
        WindowUtils.centralizarTela(this);
        olaUserJLabel.setText("Olá!");
        TableUtils.configureNonEditableTable(ordensjTable);
    }

    private void carregarOrdensParaTabela() { 
        List<String[]> ordens = ordemController.carregarOrdensServico();

        // Define as colunas da tabela
        String[] colunas = {"ID", "Descrição", "Local", "Data de Entrada", "Prioridade", "Status", "Técnico"};

        // Cria o modelo da tabela
        DefaultTableModel tableModel = new DefaultTableModel(colunas, 0);

        // Adiciona cada ordem de serviço na tabela, ignorando a primeira linha (cabeçalho)
        boolean primeiraLinha = true;
        for (String[] ordem : ordens) {
            if (primeiraLinha) {
                primeiraLinha = false; // Ignora o cabeçalho
                continue;
            }
            tableModel.addRow(ordem);
        }

        // Vincula o modelo à JTable
        ordensjTable.setModel(tableModel);
    }

    public void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) ordensjTable.getModel();
        model.setRowCount(0); // Limpa todas as linhas atuais

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/db/ordens.csv"))) {
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
            JOptionPane.showMessageDialog(this, "Erro ao ler o arquivo de ordens.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void atualizarArquivoCSV() {
        DefaultTableModel model = (DefaultTableModel) ordensjTable.getModel();
        List<String[]> dados = new ArrayList<>();

        // Lê os dados da tabela para a lista
        for (int i = 0; i < model.getRowCount(); i++) {
            String[] linha = new String[model.getColumnCount()];
            for (int j = 0; j < model.getColumnCount(); j++) {
                linha[j] = model.getValueAt(i, j).toString();
            }
            dados.add(linha);
        }

        // Escreve os dados atualizados no arquivo CSV
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/db/ordens.csv"))) {
            // Adiciona o cabeçalho ao arquivo CSV
            bw.write("ID,Descrição,Local,Data de Entrada,Prioridade,Status,Técnico");
            bw.newLine();
            
            // Escreve os dados das ordens
            for (String[] linha : dados) {
                bw.write(String.join(",", linha));
                bw.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar o arquivo CSV.", "Erro", JOptionPane.ERROR_MESSAGE);
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

        mainJPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        imgLogoJLabel = new javax.swing.JLabel();
        desenvolvedorJButton = new javax.swing.JButton();
        gerenciarTecnicosjButton = new javax.swing.JButton();
        relatoriosjButton = new javax.swing.JButton();
        encerrarjButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        olaUserJLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ordensjTable = new javax.swing.JTable();
        registrarJButton = new javax.swing.JButton();
        editarJButton = new javax.swing.JButton();
        atribuirTecnicoJButton = new javax.swing.JButton();
        excluirJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Principal - Predium");
        setResizable(false);

        mainJPanel.setBackground(new java.awt.Color(245, 245, 245));
        mainJPanel.setToolTipText("");

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        imgLogoJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgLogoJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/predium-logo+text-small.png"))); // NOI18N
        imgLogoJLabel.setText("jLabel6");

        desenvolvedorJButton.setBackground(new java.awt.Color(30, 149, 115));
        desenvolvedorJButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        desenvolvedorJButton.setForeground(new java.awt.Color(255, 255, 255));
        desenvolvedorJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/dev-icon.png"))); // NOI18N
        desenvolvedorJButton.setText("Desenvolvedor");
        desenvolvedorJButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        desenvolvedorJButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        desenvolvedorJButton.setIconTextGap(10);
        desenvolvedorJButton.setInheritsPopupMenu(true);
        desenvolvedorJButton.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        desenvolvedorJButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        desenvolvedorJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desenvolvedorJButtonActionPerformed(evt);
            }
        });

        gerenciarTecnicosjButton.setBackground(new java.awt.Color(30, 149, 115));
        gerenciarTecnicosjButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        gerenciarTecnicosjButton.setForeground(new java.awt.Color(255, 255, 255));
        gerenciarTecnicosjButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/technician-icon.png"))); // NOI18N
        gerenciarTecnicosjButton.setText("Gerenciar Técnicos");
        gerenciarTecnicosjButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gerenciarTecnicosjButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gerenciarTecnicosjButton.setIconTextGap(12);
        gerenciarTecnicosjButton.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        gerenciarTecnicosjButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        gerenciarTecnicosjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gerenciarTecnicosjButtonActionPerformed(evt);
            }
        });

        relatoriosjButton.setBackground(new java.awt.Color(30, 149, 115));
        relatoriosjButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        relatoriosjButton.setForeground(new java.awt.Color(255, 255, 255));
        relatoriosjButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/report-icon.png"))); // NOI18N
        relatoriosjButton.setText("Relatórios");
        relatoriosjButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        relatoriosjButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        relatoriosjButton.setIconTextGap(10);
        relatoriosjButton.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        relatoriosjButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        relatoriosjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatoriosjButtonActionPerformed(evt);
            }
        });

        encerrarjButton.setBackground(new java.awt.Color(30, 149, 115));
        encerrarjButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        encerrarjButton.setForeground(new java.awt.Color(255, 255, 255));
        encerrarjButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/close-icon.png"))); // NOI18N
        encerrarjButton.setText("Encerrar");
        encerrarjButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        encerrarjButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        encerrarjButton.setIconTextGap(10);
        encerrarjButton.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        encerrarjButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        encerrarjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encerrarjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(imgLogoJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
                .addComponent(gerenciarTecnicosjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(relatoriosjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(desenvolvedorJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(encerrarjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {desenvolvedorJButton, encerrarjButton, gerenciarTecnicosjButton, relatoriosjButton});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(imgLogoJLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gerenciarTecnicosjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(relatoriosjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(desenvolvedorJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(encerrarjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {desenvolvedorJButton, encerrarjButton, gerenciarTecnicosjButton});

        olaUserJLabel.setForeground(new java.awt.Color(0, 0, 0));
        olaUserJLabel.setText("Olá!");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Utilize o menu acima para acessar as funções");

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("ou selecione entre os itens abaixo:");

        ordensjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Descrição", "Local", "Prioridade", "Técnico", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ordensjTable.setFocusable(false);
        ordensjTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ordensjTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ordensjTable.getTableHeader().setResizingAllowed(false);
        ordensjTable.getTableHeader().setReorderingAllowed(false);
        ordensjTable.setVerifyInputWhenFocusTarget(false);
        jScrollPane1.setViewportView(ordensjTable);

        registrarJButton.setBackground(new java.awt.Color(30, 149, 115));
        registrarJButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        registrarJButton.setForeground(new java.awt.Color(255, 255, 255));
        registrarJButton.setText("Registrar Ordem");
        registrarJButton.setToolTipText("Adicionar uma nova ordem de serviço");
        registrarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarJButtonActionPerformed(evt);
            }
        });

        editarJButton.setBackground(new java.awt.Color(30, 149, 115));
        editarJButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        editarJButton.setForeground(new java.awt.Color(255, 255, 255));
        editarJButton.setText("Editar Ordem");
        editarJButton.setToolTipText("Editar ordem de serviço selecionada");
        editarJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarJButtonActionPerformed(evt);
            }
        });

        atribuirTecnicoJButton.setBackground(new java.awt.Color(30, 149, 115));
        atribuirTecnicoJButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        atribuirTecnicoJButton.setForeground(new java.awt.Color(255, 255, 255));
        atribuirTecnicoJButton.setText("Atribuir Técnico");
        atribuirTecnicoJButton.setToolTipText("Atribuir Tecnico a Ordem de Serviço selecionada");
        atribuirTecnicoJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atribuirTecnicoJButtonActionPerformed(evt);
            }
        });

        excluirJButton.setBackground(new java.awt.Color(30, 149, 115));
        excluirJButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        excluirJButton.setForeground(new java.awt.Color(255, 255, 255));
        excluirJButton.setText("Excluir Ordem");
        excluirJButton.setToolTipText("Excluir Ordem de Serviço selecionada");
        excluirJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainJPanelLayout = new javax.swing.GroupLayout(mainJPanel);
        mainJPanel.setLayout(mainJPanelLayout);
        mainJPanelLayout.setHorizontalGroup(
            mainJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainJPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mainJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(olaUserJLabel)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 883, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainJPanelLayout.createSequentialGroup()
                        .addComponent(registrarJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(editarJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(atribuirTecnicoJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(excluirJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(76, 76, 76))
            .addGroup(mainJPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        mainJPanelLayout.setVerticalGroup(
            mainJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(olaUserJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(mainJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registrarJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editarJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(atribuirTecnicoJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(excluirJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 999, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void excluirJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirJButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = ordensjTable.getSelectedRow();
        if (selectedRow >= 0) {
            int confirmation = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja excluir esta ordem?",
                    "Confirmar Exclusão",
                    JOptionPane.YES_NO_OPTION);

            if (confirmation == JOptionPane.YES_OPTION) {
                // Remove a ordem da tabela
                ((DefaultTableModel) ordensjTable.getModel()).removeRow(selectedRow);
                // Atualiza o arquivo CSV
                atualizarArquivoCSV();
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Selecione uma ordem para excluir.",
                    "Nenhuma Ordem Selecionada",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_excluirJButtonActionPerformed

    private void atribuirTecnicoJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atribuirTecnicoJButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_atribuirTecnicoJButtonActionPerformed

    private void editarJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarJButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editarJButtonActionPerformed

    private void registrarJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarJButtonActionPerformed
        // TODO add your handling code here:
        RegistrarOrdemJDialog dialog = new RegistrarOrdemJDialog(this, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_registrarJButtonActionPerformed

    private void encerrarjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encerrarjButtonActionPerformed
        // TODO add your handling code here:
        int resposta = JOptionPane.showOptionDialog(
                this,
                "Deseja realmente encerrar a aplicação?",
                "Confirmar Encerramento",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Sim", "Não"}, // Aqui você define os textos personalizados
                "Sim" // Define o botão "Sim" como o padrão selecionado
        );

        if (resposta == JOptionPane.YES_OPTION) {
            System.exit(0); // Fecha a aplicação
        }
    }//GEN-LAST:event_encerrarjButtonActionPerformed

    private void relatoriosjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatoriosjButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_relatoriosjButtonActionPerformed

    private void desenvolvedorJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desenvolvedorJButtonActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Desenvolvedor: Marques Vinícius Melo Martins");
    }//GEN-LAST:event_desenvolvedorJButtonActionPerformed

    private void gerenciarTecnicosjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gerenciarTecnicosjButtonActionPerformed
        // TODO add your handling code here:
        GerenciarTecnicosJFrame gerenciarTecnicosFrame = new GerenciarTecnicosJFrame();
        gerenciarTecnicosFrame.setVisible(true);

    }//GEN-LAST:event_gerenciarTecnicosjButtonActionPerformed

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
            java.util.logging.Logger.getLogger(PrincipalJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atribuirTecnicoJButton;
    private javax.swing.JButton desenvolvedorJButton;
    private javax.swing.JButton editarJButton;
    private javax.swing.JButton encerrarjButton;
    private javax.swing.JButton excluirJButton;
    private javax.swing.JButton gerenciarTecnicosjButton;
    private javax.swing.JLabel imgLogoJLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel mainJPanel;
    private javax.swing.JLabel olaUserJLabel;
    private javax.swing.JTable ordensjTable;
    private javax.swing.JButton registrarJButton;
    private javax.swing.JButton relatoriosjButton;
    // End of variables declaration//GEN-END:variables
}
