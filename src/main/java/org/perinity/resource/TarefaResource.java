package org.perinity.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.perinity.model.Pessoa;
import org.perinity.model.Tarefa;
import org.perinity.model.DTO.TarefaDTO;

import io.quarkus.panache.common.Page;
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

@Path("/tarefas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TarefaResource {

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
	public Response alocarPessoaNaTarefa(@PathParam("id") Long id, Pessoa pessoa) {
		Tarefa tarefa = Tarefa.findById(id);
		if (tarefa == null) {
			throw new WebApplicationException("Tarefa with id of " + id + " does not exist.", 404);
		}

		tarefa.pessoa = pessoa;
		tarefa.persist();

		return Response.ok(tarefa).build();
	}

	@PUT
	@Path("/finalizar/{id}")
	public Response finalizarTarefa(@PathParam("id") Long id) {
		Tarefa tarefa = Tarefa.findById(id);
		if (tarefa == null) {
			throw new WebApplicationException("Tarefa with id of " + id + " does not exist.", 404);
		}

		tarefa.finalizado = true;
		tarefa.persist();

		return Response.ok(tarefa).build();
	}

	@GET
	@Path("/pendentes")
	public List<TarefaDTO> listarTarefasPendentes() {
		// Listar 3 tarefas que estejam sem pessoa alocada com os prazos mais antigos
		List<Tarefa> tarefas = Tarefa.list("pessoa is null order by prazo asc", Page.ofSize(3));

		return tarefas.stream().map(TarefaDTO::new).collect(Collectors.toList());
	}
}
