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
            boolean primeiraLinha = true; // Flag para ignorar o cabeçalho
            while ((linha = reader.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false; // Ignora o cabeçalho
                    continue;
                }

                String[] dados = linha.split(",");
                // Verifica se o primeiro dado (ID) é numérico antes de tentar converter
                try {
                    int id = Integer.parseInt(dados[0]);
                    if (id > maiorId) {
                        maiorId = id;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Erro ao processar ID no arquivo de ordens: " + dados[0]);
                }
            }
            contadorId = maiorId + 1; // O próximo ID será o maior ID + 1
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo de ordens: " + e.getMessage());
        }
    }

    public void adicionarOrdem(OrdemServico ordem) {
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

    public List<String[]> carregarOrdensServico() {
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

    public static int gerarNovoId() {
        return contadorId++;
    }
}
