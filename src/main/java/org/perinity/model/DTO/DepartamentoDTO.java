package org.perinity.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DepartamentoDTO {
	public Long id;
	public String titulo;
	public Long quantidadePessoas;
	public Long quantidadeTarefas;
	public DepartamentoDTO() {
	}
}
