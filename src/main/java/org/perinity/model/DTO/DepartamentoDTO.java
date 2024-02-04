package org.perinity.model.DTO;

public class DepartamentoDTO {
	
    public Long id;
    
    public String titulo;
    
    public Long quantidadePessoas;
    
    public Long quantidadeTarefas;
    
    public DepartamentoDTO() {
    	
    }

    public DepartamentoDTO(Long id, String titulo, Long quantidadePessoas, Long quantidadeTarefas) {
        this.id = id;
        this.titulo = titulo;
        this.quantidadePessoas = quantidadePessoas;
        this.quantidadeTarefas = quantidadeTarefas;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getQuantidadePessoas() {
		return quantidadePessoas;
	}

	public void setQuantidadePessoas(Long quantidadePessoas) {
		this.quantidadePessoas = quantidadePessoas;
	}

	public Long getQuantidadeTarefas() {
		return quantidadeTarefas;
	}

	public void setQuantidadeTarefas(Long quantidadeTarefas) {
		this.quantidadeTarefas = quantidadeTarefas;
	}
}
