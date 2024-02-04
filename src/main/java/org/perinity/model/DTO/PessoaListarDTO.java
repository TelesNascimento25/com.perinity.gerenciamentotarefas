package org.perinity.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PessoaListarDTO {
	public Long id;
	public String nome;
	public String tituloDepartamento;
	public Long totalHoras;

	public PessoaListarDTO() {
	}
}
