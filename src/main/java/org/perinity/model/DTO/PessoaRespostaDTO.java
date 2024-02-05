package org.perinity.model.DTO;

import org.perinity.model.Pessoa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PessoaRespostaDTO {
	private Long id;
	private String nome;
	private String tituloDepartamento;
	public PessoaRespostaDTO(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.tituloDepartamento = pessoa.getDepartamento().getTitulo();
	}
}
