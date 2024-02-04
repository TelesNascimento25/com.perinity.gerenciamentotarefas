package org.perinity.resource;

import java.util.ArrayList;
import java.util.List;

import org.perinity.model.Departamento;
import org.perinity.model.DTO.DepartamentoDTO;

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

@Path("/departamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepartamentoResource {

	@POST
	@Transactional
	public Response adicionarDepartamento(Departamento departamento) {
		if (departamento.id != null) {
			throw new WebApplicationException("Id was invalidly set on request.", 422);
		}

		departamento.persist();

		return Response.ok(departamento).status(201).build();
	}

	@PUT
	@Path("/{id}")
	@Transactional
	public Response alterarDepartamento(@PathParam("id") Long id, Departamento departamento) {
		Departamento departamentoExistente = Departamento.findById(id);
		if (departamentoExistente == null) {
			throw new WebApplicationException("Departamento with id of " + id + " does not exist.", 404);
		}

		departamentoExistente.titulo = departamento.titulo;
		departamentoExistente.persist();

		return Response.ok(departamentoExistente).build();
	}

	public List<DepartamentoDTO> listarDepartamentos() {
	    List<Departamento> departamentos = Departamento.listAll();

	    List<DepartamentoDTO> departamentoDTOs = new ArrayList<>();
	    for (Departamento departamento : departamentos) {
	        long quantidadePessoas = departamento.getPessoas().size();
	        long quantidadeTarefas = departamento.getPessoas().stream()
	            .flatMap(pessoa -> pessoa.getTarefas().stream())
	            .count();
	        departamentoDTOs.add(
	                new DepartamentoDTO(departamento.id, departamento.titulo, quantidadePessoas, quantidadeTarefas));
	    }

	    return departamentoDTOs;
	}
}
