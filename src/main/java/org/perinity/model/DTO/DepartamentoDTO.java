package org.perinity.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DepartamentoDTO {
	private Long id;
	private String titulo;
	private Long quantidadePessoas;
	private Long quantidadeTarefas;
	public DepartamentoDTO() {
	}
}
