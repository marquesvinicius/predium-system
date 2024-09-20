/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.predium.controller;

/**
 *
 * @author MarquesV
 */
import com.mycompany.predium.model.Usuario;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CadastroHandler {
    private static final String CAMINHO_CSV = "src/main/resources/db/usuarios.csv";
    private List<Usuario> usuarios = new ArrayList<>();

    public CadastroHandler() {
        carregarUsuarios();
    }

    private void carregarUsuarios() {
        try (BufferedReader br = new BufferedReader(new FileReader(CAMINHO_CSV))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dadosUsuario = linha.split(",");
                if (dadosUsuario.length == 2) {
                    usuarios.add(new Usuario(dadosUsuario[0], dadosUsuario[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean usernameExiste(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public void cadastrarUsuario(String username, String senha) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_CSV, true))) {
            bw.write(username + "," + senha);
            bw.newLine();
            // Atualiza a lista em memória
            usuarios.add(new Usuario(username, senha));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}