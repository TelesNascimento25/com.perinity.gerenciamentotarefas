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
		if (tarefa.getId() != null) {
			throw new BadRequestException("Id foi definido de forma inválida na solicitação.");
		}

		if (tarefa.getDepartamento() == null) {
			throw new BadRequestException("Departamento não pode ser nulo.");
		}

		if (tarefa.getPessoa() != null && tarefa.getPessoa().getId() != null) {
			Pessoa pessoa = Pessoa.findById(tarefa.getPessoa().getId());
			if (pessoa == null) {
				throw new WebApplicationException("Pessoa com o id: " + tarefa.getPessoa().getId() + " não existe.", 404);
			}
			if (!pessoa.getDepartamento().getId().equals(tarefa.getDepartamento().getId())) {
				throw new WebApplicationException("Pessoa e Tarefa não pertencem ao mesmo departamento", 400);
			}
			tarefa.setPessoa(pessoa);
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

		Pessoa pessoaExistente = Pessoa.findById(pessoa.getId());
		if (pessoaExistente == null) {
			throw new WebApplicationException("Pessoa com o id: " + pessoa.getId() + " não existe.", 404);
		}

		if (!pessoaExistente.getDepartamento().equals(tarefa.getDepartamento())) {
			throw new WebApplicationException("Pessoa e Tarefa não pertencem ao mesmo departamento", 400);
		}

		tarefa.setPessoa(pessoaExistente);
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

		tarefa.setFinalizado(true);
		tarefa.persist();

		return tarefa;
	}

	public List<TarefaDTO> getTarefasPendentes() {
		List<Tarefa> tarefas = Tarefa.find("pessoa is null order by prazo").page(Page.ofSize(3)).list();

		if (tarefas.isEmpty()) {
			throw new WebApplicationException("Nenhuma tarefa pendente encontrada.", 404);
		}

		return tarefas.stream().map(tarefa -> {
			if (tarefa.getPessoa() == null) {
				return new TarefaDTO(tarefa);
			} else {
				return null;
			}
		}).filter(Objects::nonNull).collect(Collectors.toList());
	}
}
