package org.perinity.resource;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.perinity.model.Pessoa;
import org.perinity.model.Tarefa;
import org.perinity.model.DTO.TarefaDTO;

import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/tarefas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TarefaResource {

	private static final ModelMapper modelMapper = new ModelMapper();

	@POST
	@Transactional
	public Response adicionarTarefa(Tarefa tarefa) {
		if (tarefa.id != null) {
			throw new WebApplicationException("Id was invalidly set on request.", 422);
		}

		tarefa.persist();

		return Response.ok(tarefa).status(201).build();
	}

	@PUT
	@Path("/alocar/{id}")
	@Transactional
	public Response alocarPessoaNaTarefa(@PathParam("id") Long id, Pessoa pessoa) {
		Tarefa tarefa = Tarefa.findById(id);
		if (tarefa == null) {
			throw new WebApplicationException("Tarefa com o id: " + id + " não existe.", 404);
		}

		Pessoa pessoaExistente = Pessoa.findById(pessoa.id);
		if (pessoaExistente == null) {
			throw new WebApplicationException("Pessoa com o id: " + pessoa.id + " não existe.", 404);
		}

		if (!pessoaExistente.departamento.equals(tarefa.departamento)) {
			throw new WebApplicationException("Pessoa and Tarefa não pertencem ao mesmo departamento", 400);
		}

		tarefa.pessoa = pessoaExistente;
		tarefa.persist();

		TarefaDTO tarefaDTO = modelMapper.map(tarefa, TarefaDTO.class);

		return Response.ok(tarefaDTO).build();
	}

	@PUT
	@Path("/finalizar/{id}")
	@Transactional
	public Response finalizarTarefa(@PathParam("id") Long id) {
		Tarefa tarefa = Tarefa.findById(id);
		if (tarefa == null) {
			throw new WebApplicationException("Tarefa with id of " + id + " does not exist.", 404);
		}

		tarefa.finalizado = true;
		tarefa.persist();

		TarefaDTO tarefaDTO = modelMapper.map(tarefa, TarefaDTO.class);

		return Response.ok(tarefaDTO).build();
	}

	@GET
	@Path("/pendentes")
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
