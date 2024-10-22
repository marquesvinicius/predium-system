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
import com.mycompany.predium.controller.TecnicoController;
import com.mycompany.predium.model.Usuario;
import com.mycompany.predium.view.GerenciarTecnicosJFrame;
import com.mycompany.predium.view.PrincipalJFrame;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class FileWatcher extends Thread {

    private final Path path;
    private final PrincipalJFrame principalFrame;
    private final GerenciarTecnicosJFrame gerenciarTecnicosFrame;
    private final LoginHandler loginHandler;
    private final TecnicoController tecnicoController;
    private final Set<String> usuariosExistentes;
    private final AtomicBoolean running;
    private final Map<String, Long> lastModifiedTimes;
    private static final long DEBOUNCE_TIME = 100; // 100ms de debounce

    // Construtor para ordens de serviço
    public FileWatcher(Path path, PrincipalJFrame principalFrame, LoginHandler loginHandler) {
        this(path, principalFrame, null, loginHandler, null);
    }

    // Construtor para usuários
    public FileWatcher(Path path, LoginHandler loginHandler) {
        this(path, null, null, loginHandler, null);
    }

    // Construtor para técnicos
    public FileWatcher(Path path, GerenciarTecnicosJFrame gerenciarTecnicosFrame, TecnicoController tecnicoController) {
        this(path, null, gerenciarTecnicosFrame, null, tecnicoController);
    }

    // Construtor privado principal
    private FileWatcher(Path path, PrincipalJFrame principalFrame,
            GerenciarTecnicosJFrame gerenciarTecnicosFrame,
            LoginHandler loginHandler, TecnicoController tecnicoController) {
        this.path = path;
        this.principalFrame = principalFrame;
        this.gerenciarTecnicosFrame = gerenciarTecnicosFrame;
        this.loginHandler = loginHandler;
        this.tecnicoController = tecnicoController;
        this.usuariosExistentes = ConcurrentHashMap.newKeySet();
        this.running = new AtomicBoolean(true);
        this.lastModifiedTimes = new ConcurrentHashMap<>();

        setName("FileWatcher-Thread");
        setDaemon(true); // Thread será encerrada quando a aplicação fechar
    }

    @Override
    public void run() {
        try (WatchService watcher = FileSystems.getDefault().newWatchService()) {
            path.register(watcher, ENTRY_MODIFY);

            while (running.get()) {
                WatchKey key;
                try {
                    key = watcher.take();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }

                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == ENTRY_MODIFY) {
                        handleFileModification((Path) event.context());
                    }
                }

                if (!key.reset()) {
                    break;
                }
            }
        } catch (IOException e) {
            logError("Erro ao monitorar diretório", e);
        }
    }

    private void handleFileModification(Path modifiedFile) {
        String fileName = modifiedFile.toString();
        long currentTime = System.currentTimeMillis();
        Long lastModified = lastModifiedTimes.get(fileName);

        // Verifica se já passou tempo suficiente desde a última atualização
        if (lastModified != null && currentTime - lastModified < DEBOUNCE_TIME) {
            return;
        }

        lastModifiedTimes.put(fileName, currentTime);

        SwingUtilities.invokeLater(() -> {
            try {
                switch (fileName) {
                    case "ordens.csv":
                        updateOrdens();
                        break;
                    case "tecnicos.csv":
                        updateTecnicos();
                        break;
                    case "usuarios.csv":
                        updateUsuarios();
                        break;
                }
            } catch (Exception e) {
                logError("Erro ao processar arquivo " + fileName, e);
            }
        });
    }

    private void updateOrdens() {
        if (principalFrame != null) {
            principalFrame.atualizarTabela();
        }
    }

    private void updateTecnicos() {
        if (gerenciarTecnicosFrame != null) {
            gerenciarTecnicosFrame.atualizarTabelaTecnicos();
        }
        if (principalFrame != null) {
            principalFrame.atualizarTabela();
        }
    }

    private void updateUsuarios() {
        if (loginHandler != null) {
            List<Usuario> novosUsuarios = carregarUsuariosDoArquivo();
            loginHandler.atualizarUsuarios(novosUsuarios);

            // Atualiza o set de usuários existentes
            novosUsuarios.stream()
                    .map(Usuario::getUsername)
                    .forEach(usuariosExistentes::add);
        }
    }

    private List<Usuario> carregarUsuariosDoArquivo() {
        List<Usuario> usuarios = new ArrayList<>();
        Path arquivoUsuarios = path.resolve("usuarios.csv");

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoUsuarios.toFile()))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 2) {
                    usuarios.add(new Usuario(dados[0].trim(), dados[1].trim()));
                }
            }
        } catch (IOException e) {
            logError("Erro ao carregar usuários", e);
        }

        return usuarios;
    }

    private void logError(String message, Exception e) {
        System.err.println(message);
        e.printStackTrace();

        SwingUtilities.invokeLater(()
                -> JOptionPane.showMessageDialog(null,
                        message + ": " + e.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE)
        );
    }

    public void stopWatching() {
        running.set(false);
        interrupt();
    }
}
