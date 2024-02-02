package org.perinity.model;

import java.io.Serializable;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Pessoa extends PanacheEntityBase implements Serializable{
	private static final long serialVersionUID = -7838128553759488625L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public String nome;
	
	public String departamento;
	
	@OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER)
	public List<Tarefa> tarefas;

	public Pessoa(Long id, String nome, String departamento, List<Tarefa> tarefas) {
		this.id = id;
		this.nome = nome;
		this.departamento = departamento;
		this.tarefas = tarefas;
	}
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDepartamento() {
		return departamento;
	}

	public Pessoa() {
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}
	
}