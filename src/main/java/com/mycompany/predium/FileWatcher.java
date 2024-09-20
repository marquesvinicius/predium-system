/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.predium;

/**
 *
 * @author MarquesV
 */
import com.mycompany.predium.controller.LoginHandler;
import com.mycompany.predium.model.Usuario;
import com.mycompany.predium.view.PrincipalJFrame;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileWatcher extends Thread {

    private final Path path;
    private final PrincipalJFrame principalFrame;
    private final LoginHandler loginHandler;
    private final Set<String> usuariosExistentes = new HashSet<>(); 

    // Construtor para ordens de serviço
    public FileWatcher(Path path, PrincipalJFrame principalFrame, LoginHandler loginHandler) {
        this.path = path;
        this.principalFrame = principalFrame;
        this.loginHandler = loginHandler;
    }

    // Construtor para usuários
    public FileWatcher(Path path, LoginHandler loginHandler) {
        this.path = path;
        this.principalFrame = null; // Não precisa de PrincipalJFrame
        this.loginHandler = loginHandler;
    }

    @Override
    public void run() {
        try (WatchService watcher = FileSystems.getDefault().newWatchService()) {
            path.register(watcher, ENTRY_MODIFY);

            while (true) {
                WatchKey key;
                try {
                    key = watcher.take();
                } catch (InterruptedException e) {
                    return;
                }

                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == ENTRY_MODIFY) {
                        List<Usuario> novosUsuarios = carregarUsuariosDoArquivo();

                        // Atualiza usuários se LoginHandler não for nulo
                        if (loginHandler != null) {
                            loginHandler.atualizarUsuarios(novosUsuarios);
                            for (Usuario usuario : novosUsuarios) {
                                if (!usuariosExistentes.contains(usuario.getUsername())) {
                                    usuariosExistentes.add(usuario.getUsername());
                                }
                            }
                        }

                        // Atualiza a tabela se PrincipalJFrame não for nulo
                        if (principalFrame != null) {
                            principalFrame.atualizarTabela();
                        }
                    }
                }

                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para carregar usuários do arquivo
    private List<Usuario> carregarUsuariosDoArquivo() {
        List<Usuario> usuarios = new ArrayList<>();
        String caminhoArquivo = path.toString() + "/usuarios.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 2) {
                    String username = dados[0].trim();
                    String senha = dados[1].trim();
                    Usuario usuario = new Usuario(username, senha);
                    usuarios.add(usuario);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usuarios;
    }
}