package org.perinity.model;

import java.time.Duration;
import java.time.LocalDate;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Tarefa extends PanacheEntityBase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public String titulo;
	
	public String descricao;
	
	public LocalDate prazo;
	
	public String departamento;
	
	public Duration duracao;
	
	@ManyToOne
	public Pessoa pessoa;
	
	public Boolean finalizado;
	
	public Tarefa(Long id, String titulo, String descricao, LocalDate prazo, String departamento, Duration duracao,
			Pessoa pessoa, Boolean finalizado) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.prazo = prazo;
		this.departamento = departamento;
		this.duracao = duracao;
		this.pessoa = pessoa;
		this.finalizado = finalizado;
	}
	
	public Tarefa() {
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

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}
}