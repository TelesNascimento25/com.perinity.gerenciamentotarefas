package org.perinity.model.DTO;

import java.util.List;

import org.perinity.model.Pessoa;
import org.perinity.model.Tarefa;

public class PessoaDTO {
	
public Long id;
    
    public String nome;
    
    public Long departamentoId;
    
    public List<TarefaDTO> tarefas;
    
    public Long totalHoras;
    
    public Double mediaHoras;
    
    public PessoaDTO() {
    	
    }
    
    public PessoaDTO(Pessoa pessoa) {
        this.id = pessoa.id;
        this.nome = pessoa.nome;
        this.departamentoId = pessoa.departamento.id;

        this.totalHoras = calcularTotalHoras(pessoa.tarefas);
        this.mediaHoras = calcularMediaHoras(pessoa.tarefas);
    }

    public Long calcularTotalHoras(List<Tarefa> tarefas) {
        return tarefas.stream()
                .mapToLong(tarefa -> tarefa.duracao.toHours())
                .sum();
    }
    
    public Double calcularMediaHoras(List<Tarefa> tarefas) {
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

	public Long getDepartamentoId() {
		return departamentoId;
	}

	public void setDepartamentoId(Long departamentoId) {
		this.departamentoId = departamentoId;
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
