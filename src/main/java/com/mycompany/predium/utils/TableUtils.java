/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.predium.utils;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TableUtils {

    public static class NonEditableTableModel extends DefaultTableModel {

        public NonEditableTableModel(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Makes all cells non-editable
        }
    }

    // Método para configurar a JTable como não editável
    public static void configureNonEditableTable(JTable table) {
        DefaultTableModel currentModel = (DefaultTableModel) table.getModel();
        NonEditableTableModel newModel = new NonEditableTableModel(
                convertTo2DArray(currentModel.getDataVector()),
                getColumnNames(currentModel)
        );
        table.setModel(newModel);
    }

    // Create a new non-editable model with just column names
    public static DefaultTableModel createNonEditableModel(String[] columnNames) {
        return new NonEditableTableModel(new Object[0][0], columnNames);
    }

    // Helper method to get column names
    private static Object[] getColumnNames(DefaultTableModel model) {
        int columnCount = model.getColumnCount();
        Object[] columnNames = new Object[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columnNames[i] = model.getColumnName(i);
        }
        return columnNames;
    }

    // Convert DataVector to 2D array
    private static Object[][] convertTo2DArray(Vector<?> dataVector) {
        int rowCount = dataVector.size();
        if (rowCount == 0) {
            return new Object[0][0];
        }
        Vector<?> firstRow = (Vector<?>) dataVector.get(0);
        int columnCount = firstRow.size();
        Object[][] array = new Object[rowCount][columnCount];
        for (int row = 0; row < rowCount; row++) {
            Vector<?> currentRow = (Vector<?>) dataVector.get(row);
            for (int col = 0; col < columnCount; col++) {
                array[row][col] = currentRow.get(col);
            }
        }
        return array;
    }

    public static class StatusColorConfig {

        private final Color backgroundColor;
        private final Color foregroundColor;

        public StatusColorConfig(Color backgroundColor) {
            this(backgroundColor, Color.BLACK);
        }

        public StatusColorConfig(Color backgroundColor, Color foregroundColor) {
            this.backgroundColor = backgroundColor;
            this.foregroundColor = foregroundColor;
        }
    }

    /**
     * Configura as cores das linhas da tabela baseado no status
     *
     * @param table A tabela a ser configurada
     * @param statusColumnIndex O índice da coluna que contém o status
     * @param colorMap Um mapa associando status às suas configurações de cor
     */
    public static void configurarCoresLinhas(JTable table, int statusColumnIndex,
            Map<String, StatusColorConfig> colorMap) {

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    String status = "";
                    try {
                        Object statusObj = table.getModel().getValueAt(row, statusColumnIndex);
                        status = statusObj != null ? statusObj.toString().toLowerCase() : "";
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // Log error or handle invalid column index
                        return c;
                    }

                    StatusColorConfig config = colorMap.get(status);
                    if (config != null) {
                        c.setBackground(config.backgroundColor);
                        c.setForeground(config.foregroundColor);
                    } else {
                        c.setBackground(Color.WHITE);
                        c.setForeground(Color.BLACK);
                    }
                }
                return c;
            }
        });
    }

    /**
     * Configura as cores padrão para ordens de serviço
     *
     * @param table A tabela a ser configurada
     * @param statusColumnIndex O índice da coluna que contém o status
     */
    public static void configurarCoresOrdemServico(JTable table, int statusColumnIndex) {
        Map<String, StatusColorConfig> colorMap = new HashMap<>();

        colorMap.put("aberta", new StatusColorConfig(Color.WHITE));
        colorMap.put("andamento", new StatusColorConfig(new Color(255, 255, 200))); // Amarelo suave
        colorMap.put("concluída", new StatusColorConfig(new Color(200, 255, 200))); // Verde suave
        colorMap.put("cancelada", new StatusColorConfig(new Color(255, 200, 200))); // Vermelho suave

        configurarCoresLinhas(table, statusColumnIndex, colorMap);
    }
}
