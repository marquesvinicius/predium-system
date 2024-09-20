/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.predium.utils;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableUtils {

       // Método para configurar a JTable como não editável
    public static void configureNonEditableTable(JTable table) {
        // Pega o modelo da tabela e redefine para ser não-editável
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Cria um novo TableModel que impede a edição
        DefaultTableModel nonEditableModel = new DefaultTableModel(convertTo2DArray(model.getDataVector()), getColumnNames(model)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Torna todas as células não editáveis
            }
        };

        // Aplica o novo modelo à tabela
        table.setModel(nonEditableModel);
    }

    // Método auxiliar para obter os nomes das colunas da tabela
    private static Object[] getColumnNames(DefaultTableModel model) {
        int columnCount = model.getColumnCount();
        Object[] columnNames = new Object[columnCount];

        for (int i = 0; i < columnCount; i++) {
            columnNames[i] = model.getColumnName(i);
        }

        return columnNames;
    }

    // Converte o DataVector (Vector<Vector>) para um array 2D (Object[][])
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
}
