/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.predium.controller;

/**
 *
 * @author MarquesV
 */
import com.mycompany.predium.model.OrdemServico;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class OrdemServicoController {
    private static final String ARQUIVO_ORDENS = "src/main/resources/db/ordens.csv";
    private static final String CABECALHO_CSV = "ID,Descricao,Local,Data,Prioridade,Status,Tecnico\n";
    private static int contadorId = 1;
    private static final Logger LOGGER = Logger.getLogger(OrdemServicoController.class.getName());

    public OrdemServicoController() {
        verificarArquivo();
        atualizarContadorId();
    }

    private void verificarArquivo() {
        File file = new File(ARQUIVO_ORDENS);
        if (!file.exists()) {
            try {
                boolean created = file.getParentFile().mkdirs();
                created = file.createNewFile();
                if (created) {
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                        writer.write(CABECALHO_CSV);
                    }
                    LOGGER.info("Arquivo 'ordens.csv' criado com sucesso.");
                }
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Erro ao criar o arquivo 'ordens.csv'", e);
            }
        }
    }

    private void atualizarContadorId() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_ORDENS))) {
            String linha;
            int maiorId = 0;
            reader.readLine(); // Pula o cabeçalho
            
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                try {
                    int id = Integer.parseInt(dados[0]);
                    maiorId = Math.max(maiorId, id);
                } catch (NumberFormatException e) {
                    LOGGER.warning("ID inválido encontrado: " + dados[0]);
                }
            }
            contadorId = maiorId + 1;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao ler arquivo de ordens", e);
        }
    }

    public void adicionarOrdem(OrdemServico ordem) {
        ordem.setId(gerarNovoId());
        List<OrdemServico> ordens = carregarOrdens();
        ordens.add(ordem);
        salvarOrdens(ordens);
    }

    public List<OrdemServico> carregarOrdens() {
        List<OrdemServico> ordens = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_ORDENS))) {
            reader.readLine(); // Pula o cabeçalho
            String linha;
            while ((linha = reader.readLine()) != null) {
                ordens.add(OrdemServico.fromCSV(linha));
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao carregar ordens", e);
        }
        return ordens;
    }

    public List<String[]> carregarOrdensServicoString() {
        List<String[]> ordensList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_ORDENS))) {
            reader.readLine(); // Pula o cabeçalho
            String linha;
            while ((linha = reader.readLine()) != null) {
                ordensList.add(linha.split(","));
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao carregar ordens como string", e);
        }
        return ordensList;
    }

    public boolean atualizarStatusOrdem(String ordemId, String novoStatus) {
        try {
            List<OrdemServico> ordens = carregarOrdens();
            boolean atualizado = false;
            
            for (OrdemServico ordem : ordens) {
                if (ordem.getId().equals(Integer.parseInt(ordemId))) {
                    ordem.setStatus(novoStatus);
                    atualizado = true;
                    break;
                }
            }
            
            return atualizado && salvarOrdens(ordens);
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "ID de ordem inválido: " + ordemId, e);
            return false;
        }
    }

    public boolean removerOrdemServico(int id) {
        List<OrdemServico> ordens = carregarOrdens();
        boolean removido = ordens.removeIf(ordem -> ordem.getId() == id);
        return removido && salvarOrdens(ordens);
    }

    public boolean atribuirTecnico(int ordemId, int tecnicoId) {
        List<OrdemServico> ordens = carregarOrdens();
        boolean atualizado = false;

        for (OrdemServico ordem : ordens) {
            if (ordem.getId() == ordemId) {
                ordem.setTecnico(new TecnicoController().buscarTecnicoPorId(tecnicoId));
                atualizado = true;
                break;
            }
        }

        return atualizado && salvarOrdens(ordens);
    }

    public boolean salvarOrdens(List<OrdemServico> ordens) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_ORDENS))) {
            writer.write(CABECALHO_CSV);
            for (OrdemServico ordem : ordens) {
                writer.write(ordem.toCSV());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao salvar ordens", e);
            return false;
        }
    }

    public void removerTecnicoDaOrdem(int ordemId) {
        List<OrdemServico> ordens = carregarOrdens();
        ordens.stream()
              .filter(ordem -> ordem.getId() == ordemId)
              .findFirst()
              .ifPresent(ordem -> {
                  ordem.setTecnico(null);
                  salvarOrdens(ordens);
              });
    }

    public static int gerarNovoId() {
        return contadorId++;
    }
}