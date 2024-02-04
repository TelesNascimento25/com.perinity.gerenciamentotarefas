package org.perinity.model.DTO;

import java.io.Serializable;
import java.time.LocalDate;

import org.perinity.model.Tarefa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TarefaDTO implements Serializable {

	private static final long serialVersionUID = 9031656048095518780L;
	private Long id;
	private String titulo;
	private String descricao;
	private LocalDate prazo;
	private Long departamentoId;
	private Long duracao;
	private PessoaDTO pessoa;
	private Boolean finalizado;

	public TarefaDTO() {
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
			this.pessoa = new PessoaDTO(tarefa.pessoa.id, tarefa.pessoa.nome, tarefa.pessoa.departamento.id, null,
					null);
		}
	}
}