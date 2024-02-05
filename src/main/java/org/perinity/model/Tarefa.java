package org.perinity.model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class Tarefa extends PanacheEntityBase implements Serializable {
	private static final long serialVersionUID = -8574522383725735935L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String descricao;
	private LocalDate prazo;
	@ManyToOne(fetch = FetchType.EAGER)
	private Departamento departamento;
	private Long duracao;
	@ManyToOne(fetch = FetchType.EAGER)
	private Pessoa pessoa;
	private Boolean finalizado;
	public Tarefa() {
	}
}
