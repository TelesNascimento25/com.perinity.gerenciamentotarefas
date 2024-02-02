package org.perinity.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.perinity.model.Pessoa;
import org.perinity.model.DTO.PessoaDTO;
import org.perinity.model.DTO.TarefaDTO;

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

	 private static final ModelMapper modelMapper = new ModelMapper();
	
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
	    public List<PessoaDTO> getPessoasGastos() {
	        List<Pessoa> pessoas = Pessoa.listAll();
	        
	        if (pessoas.isEmpty()) {
	            throw new WebApplicationException("Nenhuma pessoa encontrada.", 404);
	        }

	        return pessoas.stream().map(pessoa -> {
	            PessoaDTO pessoaDTO = new PessoaDTO();
	            pessoaDTO.nome = pessoa.nome;
	            pessoaDTO.tarefas = pessoa.tarefas.stream().map(TarefaDTO::new).collect(Collectors.toList());
	            pessoaDTO.totalHoras = pessoaDTO.calcularTotalHoras(pessoa.tarefas);
	            pessoaDTO.mediaHoras = pessoaDTO.calcularMediaHoras(pessoa.tarefas);
	            return pessoaDTO;
	        }).collect(Collectors.toList());
	    }

}
	

