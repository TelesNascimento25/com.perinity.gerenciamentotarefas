package org.com.perinity.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.perinity.model.Departamento;
import org.perinity.model.Pessoa;
import org.perinity.model.Tarefa;
import org.perinity.model.DTO.PessoaGastoDTO;
import org.perinity.model.DTO.PessoaListarDTO;
import org.perinity.model.DTO.PessoaRespostaDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.WebApplicationException;

@ApplicationScoped
public class PessoaService {

	@Transactional
	public Pessoa adicionarPessoa(Pessoa pessoa) {
		if (pessoa == null) {
			throw new BadRequestException("Pessoa não pode ser nula.");
		}
		if (pessoa.getId() != null) {
			throw new BadRequestException("Id foi definido de forma inválida na solicitação.");
		}

		Departamento departamento = Departamento.findById(pessoa.getDepartamento().getId());
		if (departamento == null) {
			throw new WebApplicationException("Departamento com o id: " + pessoa.getDepartamento().getId() + " não existe.", 404);
		}

		pessoa.setDepartamento(departamento);
		pessoa.persist();

		return pessoa;
	}

	@Transactional
	public PessoaRespostaDTO alterarPessoa(Long id, Pessoa pessoa) {
		if (pessoa == null) {
			throw new BadRequestException("Pessoa não pode ser nula.");
		}
		Pessoa pessoaExistente = Pessoa.findById(id);
		if (pessoaExistente == null) {
			throw new WebApplicationException("Pessoa com o id: " + id + " não existe.", 404);
		}

		Departamento departamento = Departamento.findById(pessoa.getDepartamento().getId());
		if (departamento == null) {
			throw new WebApplicationException("Departamento com o id: " + pessoa.getDepartamento().getId() + " não existe.", 404);
		}
		pessoaExistente.setDepartamento(departamento);

		pessoaExistente.setNome(pessoa.getNome());
		pessoaExistente.persist();

		return new PessoaRespostaDTO(pessoaExistente);
	}

	@Transactional
	public String removerPessoa(Long id) {
	    Pessoa pessoa = Pessoa.findById(id);
	    if (pessoa == null) {
	        throw new WebApplicationException("Pessoa com o id: " + id + " não existe.", 404);
	    }

	    if (!pessoa.getTarefas().isEmpty()) {
	        throw new WebApplicationException("Não é possível excluir uma pessoa que está alocada em tarefas.", 400);
	    }

	    pessoa.delete();
	    return "Pessoa deletada com sucesso!";
	}


	public List<PessoaListarDTO> listarPessoas() {
		List<Pessoa> pessoas = Pessoa.listAll();

		return pessoas.stream().map(pessoa -> {
			Long totalHoras = calcularTotalHoras(pessoa.getTarefas());
			Departamento departamento = Departamento.findById(pessoa.getDepartamento().getId());
			return new PessoaListarDTO(pessoa.getId(), pessoa.getNome(), departamento.getTitulo(), totalHoras);
		}).collect(Collectors.toList());
	}

	public Long calcularTotalHoras(List<Tarefa> tarefas) {
		return tarefas.stream().mapToLong(tarefa -> tarefa.getDuracao()).sum() / 60;
	}

	public Double calcularMediaHoras(List<Tarefa> tarefas) {
		return tarefas.stream().mapToLong(tarefa -> tarefa.getDuracao()).average().orElse(0);
	}

	public List<PessoaGastoDTO> buscarGastosPorNomeEPrazo(String nome, LocalDate prazoInicio, LocalDate prazoFim) {
		List<Pessoa> pessoas = Pessoa.list("nome", nome);

		List<Pessoa> pessoasNoPrazo = pessoas.stream()
				.filter(pessoa -> pessoa.getTarefas().stream().anyMatch(
						tarefa -> !tarefa.getPrazo().isBefore(prazoInicio) && !tarefa.getPrazo().isAfter(prazoFim.plusDays(1))))
				.collect(Collectors.toList());

		List<PessoaGastoDTO> pessoasGasto = pessoasNoPrazo.stream().map(pessoa -> new PessoaGastoDTO(pessoa.getNome(),
				prazoInicio, prazoFim, calcularMediaHoras(pessoa.getTarefas()) / 60.0)).collect(Collectors.toList());

		return pessoasGasto;
	}

}
