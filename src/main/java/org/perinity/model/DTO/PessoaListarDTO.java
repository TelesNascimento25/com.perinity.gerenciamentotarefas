package org.perinity.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PessoaListarDTO {
	private Long id;
	private String nome;
	private String tituloDepartamento;
	private Long totalHoras;
	public PessoaListarDTO() {
	}
}
