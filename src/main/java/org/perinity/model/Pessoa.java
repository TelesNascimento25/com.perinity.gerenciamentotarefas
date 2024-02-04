package org.perinity.model;

import java.io.Serializable;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Pessoa extends PanacheEntityBase implements Serializable {
	private static final long serialVersionUID = -7838128553759488625L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String nome;
	@ManyToOne(fetch = FetchType.EAGER)
	public Departamento departamento;
	@OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER)
	public List<Tarefa> tarefas;
	public Pessoa() {
	}
}