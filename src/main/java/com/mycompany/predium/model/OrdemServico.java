/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.predium.model;

/**
 *
 * @author MarquesV
 */
import com.mycompany.predium.controller.TecnicoController;
import java.time.LocalDate;

public class OrdemServico {
    private int id;
    private String descricao;
    private String local;
    private LocalDate data;
    private String prioridade;
    private String status;
    private Tecnico tecnicoResponsavel;

    public static final String STATUS_ABERTA = "Aberta";
    
    // Construtor principal
    public OrdemServico(int id, String descricao, String local, String prioridade) {
        this.id = id;
        this.descricao = descricao;
        this.local = local;
        this.data = LocalDate.now();
        this.prioridade = prioridade;
        this.status = STATUS_ABERTA;
        this.tecnicoResponsavel = null;
    }

    // Construtor vazio para uso com fromCSV
    public OrdemServico() {}

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }
    
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    
    public String getPrioridade() { return prioridade; }
    public void setPrioridade(String prioridade) { this.prioridade = prioridade; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Tecnico getTecnico() { return tecnicoResponsavel; }
    public void setTecnico(Tecnico tecnico) { this.tecnicoResponsavel = tecnico; }

    // Métodos de negócio
    public void atualizarStatus(String novoStatus) {
        this.status = novoStatus;
    }

    public void atribuirTecnico(Tecnico tecnico) {
        this.tecnicoResponsavel = tecnico;
    }

    @Override
    public String toString() {
        return String.format("OrdemServico{id=%d, descricao='%s', local='%s', data=%s, " +
                           "prioridade='%s', status='%s', tecnicoResponsavel=%s}",
                           id, descricao, local, data, prioridade, status,
                           (tecnicoResponsavel != null ? tecnicoResponsavel.getNome() : "Não atribuído"));
    }

    public String toCSV() {
        String tecnicoId = (tecnicoResponsavel != null) ? String.valueOf(tecnicoResponsavel.getId()) : "null";
        return String.format("%d,%s,%s,%s,%s,%s,%s",
                           id, descricao, local, data, prioridade, status, tecnicoId);
    }

    public static OrdemServico fromCSV(String csv) {
        String[] partes = csv.split(",");
        OrdemServico ordem = new OrdemServico();
        ordem.setId(Integer.parseInt(partes[0]));
        ordem.setDescricao(partes[1]);
        ordem.setLocal(partes[2]);
        ordem.setData(LocalDate.parse(partes[3]));
        ordem.setPrioridade(partes[4]);
        ordem.setStatus(partes[5]);

        if (!"null".equals(partes[6])) {
            int tecnicoId = Integer.parseInt(partes[6]);
            ordem.setTecnico(new TecnicoController().buscarTecnicoPorId(tecnicoId));
        }

        return ordem;
    }
}