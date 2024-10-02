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

    public OrdemServico(int id, String descricao, String local, String prioridade) {
        this.id = id;
        this.descricao = descricao;
        this.local = local;
        this.data = LocalDate.now();  // A data de criação da ordem é a data atual
        this.prioridade = prioridade;
        this.status = "Aberta";  // Inicialmente, a ordem começa com o status 'Aberto'
        this.tecnicoResponsavel = null;
    }

    public OrdemServico() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTecnico(Tecnico tecnicoResponsavel) {
        this.tecnicoResponsavel = tecnicoResponsavel;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getLocal() {
        return local;
    }

    public LocalDate getData() {
        return data;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public String getStatus() {
        return status;
    }

    public Tecnico getTecnico() {
        return tecnicoResponsavel;
    }

    public void atualizarStatus(String novoStatus) {
        this.status = novoStatus;
    }

    public void atribuirTecnico(Tecnico tecnico) {
        this.tecnicoResponsavel = tecnico;
    }

    @Override
    public String toString() {
        return "OrdemServico{"
                + "id=" + id
                + ", descricao='" + descricao + '\''
                + ", local='" + local + '\''
                + ", data=" + data
                + ", prioridade='" + prioridade + '\''
                + ", status='" + status + '\''
                + ", tecnicoResponsavel=" + (tecnicoResponsavel != null ? tecnicoResponsavel.getNome() : "Não atribuído")
                + '}';
    }

    public String toCSV() {
        String tecnicoId = (tecnicoResponsavel != null) ? String.valueOf(tecnicoResponsavel.getId()) : "null";
        return id + "," + descricao + "," + local + "," + data + "," + prioridade + "," + status + "," + tecnicoId;
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

        if (!partes[6].equals("null")) {
            int tecnicoId = Integer.parseInt(partes[6]);
            ordem.setTecnico(new TecnicoController().buscarTecnicoPorId(tecnicoId));
        }

        return ordem;
    }
}
