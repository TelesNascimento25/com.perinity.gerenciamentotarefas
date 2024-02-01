package org.perinity.model.DTO;

public class DepartamentoDTO {
	
    public String departamento;
    
    public Long quantidadePessoas;
    
    public Long quantidadeTarefas;

    public DepartamentoDTO(String departamento, Long quantidadePessoas, Long quantidadeTarefas) {
        this.departamento = departamento;
        this.quantidadePessoas = quantidadePessoas;
        this.quantidadeTarefas = quantidadeTarefas;
    }

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
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