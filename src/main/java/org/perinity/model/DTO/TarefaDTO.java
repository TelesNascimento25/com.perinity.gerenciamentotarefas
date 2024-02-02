package org.perinity.model.DTO;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;

import org.perinity.model.Pessoa;
import org.perinity.model.Tarefa;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

public class TarefaDTO implements Serializable{
	
	private static final long serialVersionUID = 9031656048095518780L;

	private Long id; // deixar **privado
    
	private String titulo;
    
	private String descricao;
    
	private LocalDate prazo;
    
	private String departamento;
    
	private Duration duracao;
    
	private PessoaDTO pessoa;
    
	private Boolean finalizado;
	
	public TarefaDTO() {
	        // Initialization logic, if needed
    }

    // PODE REMOVER DEPOIS
	public TarefaDTO(Tarefa tarefa) {
	    this.id = tarefa.id;
	    this.titulo = tarefa.titulo;
	    this.descricao = tarefa.descricao;
	    this.prazo = tarefa.prazo;
	    this.departamento = tarefa.departamento;
	    this.duracao = tarefa.duracao;
	    this.finalizado = tarefa.finalizado;
	    if (tarefa.pessoa != null) {
	        this.pessoa = new PessoaDTO(tarefa.pessoa);
	    }
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getPrazo() {
		return prazo;
	}

	public void setPrazo(LocalDate prazo) {
		this.prazo = prazo;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public Duration getDuracao() {
		return duracao;
	}

	public void setDuracao(Duration duracao) {
		this.duracao = duracao;
	}

	public PessoaDTO getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaDTO pessoa) {
		this.pessoa = pessoa;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}
    
    
}