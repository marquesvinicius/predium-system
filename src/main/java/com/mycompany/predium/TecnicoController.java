/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.predium;

/**
 *
 * @author MarquesV
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TecnicoController {

    private static final String ARQUIVO_TECNICOS = "tecnicos.csv";

    public TecnicoController() {
        verificarArquivo();
    }

    private void verificarArquivo() {
        File file = new File(ARQUIVO_TECNICOS);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Arquivo 'tecnicos.csv' criado com sucesso.");
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo 'tecnicos.csv': " + e.getMessage());
            }
        }
    }

    public void adicionarTecnico(Tecnico tecnico) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_TECNICOS, true))) {
            writer.write(tecnico.toCSV());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao adicionar técnico ao arquivo: " + e.getMessage());
        }
    }

    public List<Tecnico> listarTecnicos() {
        List<Tecnico> tecnicos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_TECNICOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Tecnico tecnico = Tecnico.fromCSV(linha);
                tecnicos.add(tecnico);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de técnicos: " + e.getMessage());
        }
        return tecnicos;
    }
}
