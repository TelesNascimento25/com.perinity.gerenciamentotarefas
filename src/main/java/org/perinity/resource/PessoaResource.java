package org.perinity.resource;

import java.time.LocalDate;
import java.util.List;

import org.com.perinity.service.PessoaService;
import org.perinity.model.Pessoa;
import org.perinity.model.DTO.PessoaGastoDTO;
import org.perinity.model.DTO.PessoaListarDTO;
import org.perinity.model.DTO.PessoaRespostaDTO;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pessoas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaResource {

	@Inject
	PessoaService pessoaService;

	@POST
	public Response adicionarPessoa(Pessoa pessoa) {
		Pessoa pessoaPersistida = pessoaService.adicionarPessoa(pessoa);
		PessoaRespostaDTO pessoaResposta = new PessoaRespostaDTO(pessoaPersistida);
		return Response.ok(pessoaResposta).status(201).build();
	}

	@PUT
	@Path("/{id}")
	public Response alterarPessoa(@PathParam("id") Long id, Pessoa pessoa) {
		PessoaRespostaDTO pessoaResposta = pessoaService.alterarPessoa(id, pessoa);
		return Response.ok(pessoaResposta).build();
    }


    @DELETE
    @Path("/{id}")
    public Response removerPessoa(@PathParam("id") Long id) {
        pessoaService.removerPessoa(id);
        return Response.ok("Pessoa deletada com sucesso!").build();
    }

    @GET
    public List<PessoaListarDTO> listarPessoas() {
        return pessoaService.listarPessoas();
    }
    
    @GET
    @Path("/gastos")
    public List<PessoaGastoDTO> buscarGastosPorNomeEPrazo(@QueryParam("nome") String nome, @QueryParam("prazoInicio") String prazoInicioStr, @QueryParam("prazoFim") String prazoFimStr) {
        LocalDate prazoInicio = LocalDate.parse(prazoInicioStr);
        LocalDate prazoFim = LocalDate.parse(prazoFimStr);
        return pessoaService.buscarGastosPorNomeEPrazo(nome, prazoInicio, prazoFim);
    }



}
