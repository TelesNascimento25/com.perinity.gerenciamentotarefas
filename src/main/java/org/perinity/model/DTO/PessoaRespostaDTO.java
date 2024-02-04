package org.perinity.model.DTO;

import org.perinity.model.Pessoa;

public class PessoaRespostaDTO {
	public Long id;
	public String nome;
	public String tituloDepartamento;

	public PessoaRespostaDTO(Pessoa pessoa) {
		this.id = pessoa.id;
		this.nome = pessoa.nome;
		this.tituloDepartamento = pessoa.departamento.titulo;
	}
}
