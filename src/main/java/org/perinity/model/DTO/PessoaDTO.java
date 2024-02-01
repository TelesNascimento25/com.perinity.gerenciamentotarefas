package org.perinity.model.DTO;

import java.util.List;

import org.perinity.model.Pessoa;
import org.perinity.model.Tarefa;

public class PessoaDTO {
	
    public Long id;
    
    public String nome;
    
    public String departamento;
    
    public List<Tarefa> tarefas;
    
    public Long totalHoras;
    
    public Double mediaHoras;
    
    public PessoaDTO(Pessoa pessoa) {
        this.id = pessoa.id;
        this.nome = pessoa.nome;
        this.departamento = pessoa.departamento;
        this.tarefas = pessoa.tarefas;

        this.totalHoras = calcularTotalHoras(pessoa.tarefas);
        this.mediaHoras = calcularMediaHoras(pessoa.tarefas);
    }

    private Long calcularTotalHoras(List<Tarefa> tarefas) {
        return tarefas.stream()
                .mapToLong(tarefa -> tarefa.duracao.toHours())
                .sum();
    }
    
    private Double calcularMediaHoras(List<Tarefa> tarefas) {
        return tarefas.stream()
                .mapToLong(tarefa -> tarefa.duracao.toHours())
                .average()
                .orElse(0);
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public Long getTotalHoras() {
		return totalHoras;
	}

	public void setTotalHoras(Long totalHoras) {
		this.totalHoras = totalHoras;
	}

	public Double getMediaHoras() {
		return mediaHoras;
	}

	public void setMediaHoras(Double mediaHoras) {
		this.mediaHoras = mediaHoras;
	}
}
