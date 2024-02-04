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
public class Departamento extends PanacheEntityBase implements Serializable {

	private static final long serialVersionUID = 5132583959301321727L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	public String titulo;
	
	@OneToMany(mappedBy = "departamento", fetch = FetchType.EAGER)
	public List<Pessoa> pessoas;
	
	
	public Departamento(Long id, String titulo, List<Pessoa> pessoas) {
		this.id = id;
		this.titulo = titulo;
		this.pessoas = pessoas;
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

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
	public Departamento() {
		
	}	
}
