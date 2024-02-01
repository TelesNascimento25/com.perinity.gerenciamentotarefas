package org.perinity.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.perinity.model.Pessoa;
import org.perinity.model.DTO.PessoaDTO;

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

//    public PessoaResource(PessoaDTO pessoaDTO) {
////    	
//    }

//    public List<PessoaResource> buscarPessoasPorNomeEPeriodo(String nome, LocalDateParam inicio, LocalDateParam fim){
//        // Buscar pessoas por nome e período
//        List<Pessoa> pessoas = Pessoa.list("nome = ?1 and data >= ?2 and data <= ?3", nome, inicio.getDate(), fim.getDate());
//
//        // Converter para DTOs
//        List<PessoaDTO> pessoaDTOs = pessoas.stream().map(PessoaDTO::new).collect(Collectors.toList());
//
//        // Converter para Resources
//        List<PessoaResource> pessoaResources = pessoaDTOs.stream().map(PessoaResource::new).collect(Collectors.toList());
//
//        return pessoaResources;
//    }

	@GET
	@Transactional
	public List<PessoaDTO> listarPessoas() {
		List<Pessoa> pessoas = Pessoa.listAll();

		return pessoas.stream().map(PessoaDTO::new).collect(Collectors.toList());
	}
}
