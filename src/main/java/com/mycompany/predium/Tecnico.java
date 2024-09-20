/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.predium;

/**
 *
 * @author MarquesV
 */
public class Tecnico {
    private int id;
    private String nome;
    private String especialidade;
    
    public Tecnico(int id, String nome, String especialidade) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
    }
    
    public Tecnico(){
        
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void atribuirOrdem(OrdemServico ordem) {
        ordem.atribuirTecnico(this);
    }

    @Override
    public String toString() {
        return "Tecnico{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", especialidade='" + especialidade + '\'' +
               '}';
    }
    
    public String toCSV() {
        return id + "," + nome + "," + especialidade;
    }
    
        public static Tecnico fromCSV(String csv) {
        String[] partes = csv.split(",");
        int id = Integer.parseInt(partes[0]);
        String nome = partes[1];
        String especialidade = partes[2];

        Tecnico tecnico = new Tecnico();
        tecnico.setId(id);
        tecnico.setNome(nome);
        tecnico.setEspecialidade(especialidade);
        return tecnico;
    }
}

