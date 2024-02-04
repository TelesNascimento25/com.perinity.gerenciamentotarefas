package org.perinity.resource;

import java.util.List;

import org.com.perinity.service.DepartamentoService;
import org.perinity.model.Departamento;
import org.perinity.model.DTO.DepartamentoDTO;

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

@Path("/departamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepartamentoResource {

	@Inject
	DepartamentoService departamentoService;

	@POST
	public Response adicionarDepartamento(Departamento departamento) {
		Departamento departamentoPersistido = departamentoService.adicionarDepartamento(departamento);
		return Response.ok(departamentoPersistido).status(201).build();
	}

	@PUT
	@Path("/{id}")
	public Response alterarDepartamento(@PathParam("id") Long id, Departamento departamento) {
		Departamento departamentoAlterado = departamentoService.alterarDepartamento(id, departamento);
		return Response.ok(departamentoAlterado).build();
	}

	@GET
	public List<DepartamentoDTO> listarDepartamentos() {
		return departamentoService.listarDepartamentos();
	}
}