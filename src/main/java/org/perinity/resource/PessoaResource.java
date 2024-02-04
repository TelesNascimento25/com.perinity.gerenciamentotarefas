package org.perinity.resource;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.perinity.model.Pessoa;
import org.perinity.model.DTO.PessoaDTO;
import org.perinity.model.DTO.PessoaGastoDTO;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pessoas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaResource {

	@POST
	@Transactional
	public Response adicionarPessoa(Pessoa pessoa) {
		if (pessoa.id != null) {
			throw new WebApplicationException("Id was invalidly set on request.", 422);
		}

		pessoa.persist();

		return Response.ok(pessoa).status(201).build();
	}

	@PUT
	@Path("/{id}")
	@Transactional
	public Response alterarPessoa(@PathParam("id") Long id, Pessoa pessoa) {
		Pessoa pessoaExistente = Pessoa.findById(id);
		if (pessoaExistente == null) {
			throw new WebApplicationException("Pessoa with id of " + id + " does not exist.", 404);
		}

		pessoaExistente.nome = pessoa.nome;
		pessoaExistente.departamento = pessoa.departamento;
		pessoaExistente.persist();

		return Response.ok(pessoaExistente).build();
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	public Response removerPessoa(@PathParam("id") Long id) {
		Pessoa pessoa = Pessoa.findById(id);
		if (pessoa == null) {
			throw new WebApplicationException("Pessoa with id of " + id + " does not exist.", 404);
		}

		pessoa.delete();

		return Response.noContent().build();
	}

	@GET
	public List<PessoaDTO> listarPessoas() {
		List<Pessoa> pessoas = Pessoa.listAll();

		return pessoas.stream().map(PessoaDTO::new).collect(Collectors.toList());
	}

	@GET
	@Path("/gastos")
	public List<PessoaGastoDTO> getPessoasGastos() {
		List<Pessoa> pessoas = Pessoa.listAll();

		if (pessoas.isEmpty()) {
			throw new WebApplicationException("Nenhuma pessoa encontrada.", 404);
		}

		return pessoas.stream().map(pessoa -> {
			LocalDate periodoInicio = pessoa.tarefas.stream().min(Comparator.comparing(tarefa -> tarefa.prazo))
					.map(tarefa -> tarefa.prazo).orElse(null);
			LocalDate periodoFim = pessoa.tarefas.stream().max(Comparator.comparing(tarefa -> tarefa.prazo))
					.map(tarefa -> tarefa.prazo).orElse(null);
			Double mediaHoras = pessoa.tarefas.stream().mapToDouble(tarefa -> tarefa.duracao.toHours()).average()
					.orElse(0);
			return new PessoaGastoDTO(pessoa.nome, periodoInicio, periodoFim, mediaHoras);
		}).collect(Collectors.toList());
	}

}
