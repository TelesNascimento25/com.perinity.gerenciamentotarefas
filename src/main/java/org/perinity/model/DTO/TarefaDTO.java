package org.perinity.model.DTO;

import java.time.Duration;
import java.time.LocalDate;

import org.perinity.model.Pessoa;
import org.perinity.model.Tarefa;

public class TarefaDTO {
	
    public Long id;
    
    public String titulo;
    
    public String descricao;
    
    public LocalDate prazo;
    
    public String departamento;
    
    public Duration duracao;
    
    public Pessoa pessoa;
    
    public Boolean finalizado;

    public TarefaDTO(Tarefa tarefa) {
        this.id = tarefa.id;
        this.titulo = tarefa.titulo;
        this.descricao = tarefa.descricao;
        this.prazo = tarefa.prazo;
        this.departamento = tarefa.departamento;
        this.duracao = tarefa.duracao;
        this.pessoa = tarefa.pessoa;
        this.finalizado = tarefa.finalizado;
    }
}