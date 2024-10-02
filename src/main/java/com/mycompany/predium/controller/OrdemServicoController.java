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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrdemServicoController {

    // Caminho atualizado para o novo diretório em 'resources/db'
    private static final String ARQUIVO_ORDENS = "src/main/resources/db/ordens.csv";
    private static int contadorId = 1;

    public OrdemServicoController() {
        verificarArquivo();
        atualizarContadorId();
    }

    // Verifica se o arquivo existe e o cria se não existir
    private void verificarArquivo() {
        File file = new File(ARQUIVO_ORDENS);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Arquivo 'ordens.csv' criado com sucesso.");
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo 'ordens.csv': " + e.getMessage());
            }
        }
    }

    // Atualiza o contadorId para o maior ID presente no arquivo
    private void atualizarContadorId() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_ORDENS))) {
            String linha;
            int maiorId = 0;
            boolean primeiraLinha = true;
            while ((linha = reader.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }
                String[] dados = linha.split(",");
                try {
                    int id = Integer.parseInt(dados[0]);
                    if (id > maiorId) {
                        maiorId = id;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Erro ao processar ID no arquivo de ordens: " + dados[0]);
                }
            }
            contadorId = maiorId + 1;
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de ordens: " + e.getMessage());
        }
    }

    public void adicionarOrdem(OrdemServico ordem) {
        ordem.setId(gerarNovoId());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_ORDENS, true))) {
            writer.write(ordem.toCSV());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao adicionar ordem ao arquivo: " + e.getMessage());
        }
    }

    public List<OrdemServico> listarOrdens() {
        List<OrdemServico> ordens = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_ORDENS))) {
            String linha;
            boolean primeiraLinha = true; // Flag para ignorar o cabeçalho
            while ((linha = reader.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false; // Ignora o cabeçalho
                    continue;
                }
                OrdemServico ordem = OrdemServico.fromCSV(linha);
                ordens.add(ordem);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de ordens: " + e.getMessage());
        }
        return ordens;
    }

    public List<String[]> carregarOrdensServicoString() {
        List<String[]> ordensList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_ORDENS))) {
            String linha;
            boolean primeiraLinha = true; // Flag para ignorar o cabeçalho
            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false; // Ignora o cabeçalho
                    continue;
                }
                String[] dados = linha.split(",");
                ordensList.add(dados);  // Adiciona o array de strings com os dados da linha
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ordensList;
    }

    public boolean atualizarStatusOrdem(String ordemId, String novoStatus) {
        try {
            // Carregar ordens do arquivo CSV
            List<OrdemServico> ordens = this.carregarOrdens(); // Método para carregar as ordens

            for (OrdemServico ordem : ordens) {
                if (ordem.getId().equals(Integer.parseInt(ordemId))) {
                    ordem.setStatus(novoStatus); // Atualiza o status
                    break;
                }
            }

            // Salvar as ordens atualizadas no arquivo CSV
            return salvarOrdens(ordens); // Método para salvar as ordens

        } catch (NumberFormatException e) {
            System.err.println("Erro ao atualizar status da ordem: " + e.getMessage());
            return false;
        }
    }

    public boolean removerOrdemServico(int id) {
        List<OrdemServico> ordens = carregarOrdens();
        boolean removido = ordens.removeIf(ordem -> ordem.getId() == id);
        if (removido) {
            return salvarOrdens(ordens);
        }
        return false;
    }

    public List<OrdemServico> carregarOrdens() {
        List<OrdemServico> ordens = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_ORDENS))) {
            String linha;
            boolean primeiraLinha = true;
            while ((linha = reader.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }
                OrdemServico ordem = OrdemServico.fromCSV(linha);
                ordens.add(ordem);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de ordens: " + e.getMessage());
        }
        return ordens;
    }

    public boolean atribuirTecnico(int ordemId, int tecnicoId) {
        try {
            List<OrdemServico> ordens = this.carregarOrdens();
            boolean ordemEncontrada = false;

            for (OrdemServico ordem : ordens) {
                if (ordem.getId() == ordemId) {
                    ordem.setTecnico(new TecnicoController().buscarTecnicoPorId(tecnicoId));
                    ordemEncontrada = true;
                    break;
                }
            }

            if (ordemEncontrada) {
                return salvarOrdens(ordens);
            } else {
                System.err.println("Ordem com ID " + ordemId + " não encontrada.");
                return false;
            }

        } catch (Exception e) {
            System.err.println("Erro ao atribuir técnico à ordem: " + e.getMessage());
            return false;
        }
    }

    public boolean salvarOrdens(List<OrdemServico> ordens) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_ORDENS))) {
            writer.write("ID,Descricao,Local,Data,Prioridade,Status,Tecnico\n"); // Cabeçalho
            for (OrdemServico ordem : ordens) {
                writer.write(ordem.toCSV());
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao salvar ordens no arquivo: " + e.getMessage());
            return false;
        }
    }

    public void removerTecnicoDaOrdem(int ordemId) {
        List<OrdemServico> ordens = carregarOrdens();
        for (OrdemServico ordem : ordens) {
            if (ordem.getId() == ordemId) {
                ordem.setTecnico(null);
                break;
            }
        }
        salvarOrdens(ordens);
    }

    public static int gerarNovoId() {
        return contadorId++;
    }
}
