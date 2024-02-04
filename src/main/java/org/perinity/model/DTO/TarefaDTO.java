package org.perinity.model.DTO;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;

import org.perinity.model.Tarefa;


public class TarefaDTO implements Serializable{
	
	private static final long serialVersionUID = 9031656048095518780L;

	private Long id;
    
	private String titulo;
    
	private String descricao;
    
	private LocalDate prazo;
    
	private Long departamentoId;
    
	private Duration duracao;
    
	private PessoaDTO pessoa;
    
	private Boolean finalizado;
	
	public TarefaDTO() {
	        // Initialization logic, if needed
    }

	public TarefaDTO(Tarefa tarefa) {
	    this.id = tarefa.id;
	    this.titulo = tarefa.titulo;
	    this.descricao = tarefa.descricao;
	    this.prazo = tarefa.prazo;
	    this.departamentoId = tarefa.departamento.id;
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

	public Long getDepartamentoId() {
		return departamentoId;
	}

	public void setDepartamentoId(Long departamentoId) {
		this.departamentoId = departamentoId;
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