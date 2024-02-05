package org.perinity.model.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PessoaDTO {
	private Long id;
	private String nome;
	private Long departamentoId;
	private List<TarefaDTO> tarefas;
	private Long totalHoras;
	private Double mediaHoras;
	public PessoaDTO() {
	}
	public PessoaDTO(Long id, String nome, Long departamentoId, Long totalHoras, Double mediaHoras) {
		this.id = id;
		this.nome = nome;
		this.departamentoId = departamentoId;
		this.totalHoras = totalHoras;
		this.mediaHoras = mediaHoras;
	}
}