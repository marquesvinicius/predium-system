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
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;

public class LoginHandler {

    private List<Usuario> usuarios;

    public LoginHandler(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void atualizarUsuarios(List<Usuario> novosUsuarios) {
        this.usuarios = novosUsuarios; // Atualiza a lista de usuários com a nova lista
    }

    public void adicionarUsuario(Usuario usuario) {
        if (!usuarios.contains(usuario)) { // Verifica se o usuário já não está na lista
            usuarios.add(usuario); // Adiciona o novo usuário
        }
    }

    public boolean autenticarUsuario(String username, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username) && usuario.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    // Método para lidar com o login e retornar o sucesso ou falha
    public boolean login(String inseridoUsername, String inseridoSenha) {
        if (autenticarUsuario(inseridoUsername, inseridoSenha)) {
            JOptionPane.showMessageDialog(null, "Login bem-sucedido!");
            return true;
        } else {
            return false;
        }
    }
}