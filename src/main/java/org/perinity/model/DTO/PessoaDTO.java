package org.perinity.model.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PessoaDTO {
	public Long id;
	public String nome;
	public Long departamentoId;
	public List<TarefaDTO> tarefas;
	public Long totalHoras;
	public Double mediaHoras;

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