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
		this.id = tarefa.getId();
		this.titulo = tarefa.getTitulo();
		this.descricao = tarefa.getDescricao();
		this.prazo = tarefa.getPrazo();
		this.departamentoId = tarefa.getDepartamento().getId();
		this.duracao = tarefa.getDuracao();
		this.finalizado = tarefa.getFinalizado();
		if (tarefa.getPessoa() != null) {
			this.pessoa = new PessoaDTO(tarefa.getPessoa().getId(), tarefa.getPessoa().getNome(), tarefa.getPessoa().getDepartamento().getId(), null,
					null);
		}
	}
}