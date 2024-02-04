package org.com.perinity.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.perinity.model.Pessoa;
import org.perinity.model.Tarefa;
import org.perinity.model.DTO.TarefaDTO;

import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.WebApplicationException;

@ApplicationScoped
public class TarefaService {

	@Transactional
	public Tarefa adicionarTarefa(Tarefa tarefa) {
		if (tarefa == null) {
			throw new BadRequestException("Tarefa não pode ser nula.");
		}
		if (tarefa.id != null) {
			throw new BadRequestException("Id foi definido de forma inválida na solicitação.");
		}

		if (tarefa.departamento == null) {
			throw new BadRequestException("Departamento não pode ser nulo.");
		}

		if (tarefa.pessoa != null && tarefa.pessoa.id != null) {
			Pessoa pessoa = Pessoa.findById(tarefa.pessoa.id);
			if (pessoa == null) {
				throw new WebApplicationException("Pessoa com o id: " + tarefa.pessoa.id + " não existe.", 404);
			}
			if (!pessoa.departamento.id.equals(tarefa.departamento.id)) {
				throw new WebApplicationException("Pessoa e Tarefa não pertencem ao mesmo departamento", 400);
			}
			tarefa.pessoa = pessoa;
		}

		tarefa.persist();

		return tarefa;
	}

	@Transactional
	public TarefaDTO alocarPessoa(Long id, Pessoa pessoa) {
		if (pessoa == null) {
			throw new BadRequestException("Pessoa não pode ser nula.");
		}
		Tarefa tarefa = Tarefa.findById(id);
		if (tarefa == null) {
			throw new WebApplicationException("Tarefa com o id: " + id + " não existe.", 404);
		}

		Pessoa pessoaExistente = Pessoa.findById(pessoa.id);
		if (pessoaExistente == null) {
			throw new WebApplicationException("Pessoa com o id: " + pessoa.id + " não existe.", 404);
		}

		if (!pessoaExistente.departamento.equals(tarefa.departamento)) {
			throw new WebApplicationException("Pessoa e Tarefa não pertencem ao mesmo departamento", 400);
		}

		tarefa.pessoa = pessoaExistente;
		tarefa.persist();

		TarefaDTO tarefaDTO = new TarefaDTO(tarefa);
		return tarefaDTO;
	}

	@Transactional
	public Tarefa finalizarTarefa(Long id) {
		Tarefa tarefa = Tarefa.findById(id);
		if (tarefa == null) {
			throw new WebApplicationException("Tarefa com id de " + id + " não existe.", 404);
		}

		tarefa.finalizado = true;
		tarefa.persist();

		return tarefa;
	}

	public List<TarefaDTO> getTarefasPendentes() {
		List<Tarefa> tarefas = Tarefa.find("pessoa is null order by prazo").page(Page.ofSize(3)).list();

		if (tarefas.isEmpty()) {
			throw new WebApplicationException("Nenhuma tarefa pendente encontrada.", 404);
		}

		return tarefas.stream().map(tarefa -> {
			if (tarefa.pessoa == null) {
				return new TarefaDTO(tarefa);
			} else {
				return null;
			}
		}).filter(Objects::nonNull).collect(Collectors.toList());
	}
}
