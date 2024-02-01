package org.perinity.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.perinity.model.Pessoa;
import org.perinity.model.Tarefa;
import org.perinity.model.DTO.DepartamentoDTO;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/departamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepartamentoResource {

	@GET
	public List<DepartamentoDTO> listarDepartamentos() {
		List<String> departamentos = Pessoa.listAll().stream().map(pessoa -> ((Pessoa) pessoa).getDepartamento())
				.distinct().collect(Collectors.toList());

		List<DepartamentoDTO> departamentoDTOs = new ArrayList<>();
		for (String departamento : departamentos) {
			long quantidadePessoas = Pessoa.count("departamento", departamento);
			long quantidadeTarefas = Tarefa.count("departamento", departamento);
			departamentoDTOs.add(new DepartamentoDTO(departamento, quantidadePessoas, quantidadeTarefas));
		}

		return departamentoDTOs;
	}
}