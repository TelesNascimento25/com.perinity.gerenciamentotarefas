package org.perinity.resource;

import java.util.List;

import org.com.perinity.service.TarefaService;
import org.perinity.model.Pessoa;
import org.perinity.model.Tarefa;
import org.perinity.model.DTO.TarefaDTO;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/tarefas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TarefaResource {

	@Inject
	TarefaService tarefaService;

	@POST
	public Response adicionarTarefa(Tarefa tarefa) {
		Tarefa tarefaPersistida = tarefaService.adicionarTarefa(tarefa);
		TarefaDTO tarefaResposta = new TarefaDTO(tarefaPersistida);
		return Response.ok(tarefaResposta).status(201).build();
	}

	@PUT
	@Path("/alocar/{id}")
	public Response alocarPessoa(@PathParam("id") Long id, Pessoa pessoa) {
		TarefaDTO tarefaAlocada = tarefaService.alocarPessoa(id, pessoa);
		return Response.ok(tarefaAlocada).build();
	}

	@PUT
	@Path("/finalizar/{id}")
	public Response finalizarTarefa(@PathParam("id") Long id) {
		tarefaService.finalizarTarefa(id);
		return Response.ok("Tarefa finalizada com sucesso!").build();
	}

	@GET
	@Path("/pendentes")
	public List<TarefaDTO> getTarefasPendentes() {
		return tarefaService.getTarefasPendentes();
	}
}