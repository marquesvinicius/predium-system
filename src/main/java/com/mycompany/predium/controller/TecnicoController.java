/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.predium.controller;

/**
 *
 * @author MarquesV
 */
import com.mycompany.predium.model.Tecnico;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TecnicoController {

    private List<Tecnico> tecnicos;
    private final String CSV_FILE_PATH = "src/main/resources/db/tecnicos.csv";
    private int nextId;

    public TecnicoController() {
        tecnicos = new ArrayList<>();
        verificarOuCriarArquivo();
        carregarTecnicos();
        if (tecnicos.isEmpty()) {
            nextId = 1;
        } else {
            nextId = tecnicos.get(tecnicos.size() - 1).getId() + 1;
        }
    }

    private void verificarOuCriarArquivo() {
        File file = new File(CSV_FILE_PATH);
        if (!file.exists()) {
            try {
                File dir = file.getParentFile();
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write("id,nome,especialidade");
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String[]> carregarTecnicos() {
        List<String[]> dadosTecnicos = new ArrayList<>();
        tecnicos.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String linha = reader.readLine(); // Ignora o cabeçalho
            while ((linha = reader.readLine()) != null) {
                Tecnico tecnico = Tecnico.fromCSV(linha);
                tecnicos.add(tecnico);
                dadosTecnicos.add(new String[]{String.valueOf(tecnico.getId()), tecnico.getNome(), tecnico.getEspecialidade()});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dadosTecnicos;
    }

    private void salvarTecnicos() {
        synchronized (tecnicos) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
                writer.write("id,nome,especialidade");
                writer.newLine();
                for (Tecnico tecnico : tecnicos) {
                    writer.write(tecnico.toCSV());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void cadastrarTecnico(String nome, String especialidade) {
        synchronized (tecnicos) {
            Tecnico novoTecnico = new Tecnico(nextId, nome, especialidade);
            tecnicos.add(novoTecnico);
            nextId++;
            salvarTecnicos();
        }
    }

    public List<Tecnico> listarTecnicos() {
        return new ArrayList<>(tecnicos);
    }

    public Tecnico buscarTecnicoPorId(int id) {
        return tecnicos.stream()
                .filter(tecnico -> tecnico.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean excluirTecnico(int id) {
        synchronized (tecnicos) {
            boolean removed = tecnicos.removeIf(tecnico -> tecnico.getId() == id);
            if (removed) {
                salvarTecnicos();
                // Aqui, talvez você queira recalcular o nextId se necessário.
            }
            return removed;
        }
    }

    public boolean editarTecnico(int id, String novoNome, String novaEspecialidade) {
        for (Tecnico tecnico : tecnicos) {
            if (tecnico.getId() == id) {
                tecnico.setNome(novoNome);
                tecnico.setEspecialidade(novaEspecialidade);
                salvarTecnicos();
                return true;
            }
        }
        return false;
    }
}
