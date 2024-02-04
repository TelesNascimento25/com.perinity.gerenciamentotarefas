package org.com.perinity.service;

import java.util.ArrayList;
import java.util.List;

import org.perinity.model.Departamento;
import org.perinity.model.DTO.DepartamentoDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;

@ApplicationScoped
public class DepartamentoService {

	 @Transactional
	    public Departamento adicionarDepartamento(Departamento departamento) {
	        if (departamento == null) {
	            throw new WebApplicationException("Departamento não pode ser nulo.", 400);
	        }
	        if (departamento.id != null) {
	            throw new WebApplicationException("Id foi definido de forma inválida na solicitação.", 422);
	        }

	        departamento.persist();

	        return departamento;
	    }

	    @Transactional
	    public Departamento alterarDepartamento(Long id, Departamento departamento) {
	        if (departamento == null) {
	            throw new WebApplicationException("Departamento não pode ser nulo.", 400);
	        }
	        Departamento departamentoExistente = Departamento.findById(id);
	        if (departamentoExistente == null) {
	            throw new WebApplicationException("Departamento com o id: " + id + " não existe.", 404);
	        }

	        departamentoExistente.titulo = departamento.titulo;
	        departamentoExistente.persist();

	        return departamentoExistente;
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