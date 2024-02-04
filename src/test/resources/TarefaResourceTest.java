package org.perinity.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.com.perinity.service.TarefaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.perinity.model.Departamento;
import org.perinity.model.Pessoa;
import org.perinity.model.Tarefa;
import org.perinity.model.DTO.TarefaDTO;

class TarefaServiceTest {

    @InjectMocks
    private TarefaService tarefaService;

    @Mock
    private Tarefa tarefaMock;

    @Test
    void adicionarTarefa_DeveAdicionarTarefaCorretamente() {
        when(tarefaMock.getId()).thenReturn(null);
        when(tarefaMock.getPessoa()).thenReturn(null);
        when(tarefaMock.getDepartamento()).thenReturn(new Departamento());

        Tarefa resultado = tarefaService.adicionarTarefa(tarefaMock);

        assertNotNull(resultado);
        verify(tarefaMock, times(1)).persist();
    }

    @Test
    void alocarPessoa_AlocaPessoaNaTarefaCorretamente() {
        Long tarefaId = 1L;
        Pessoa pessoa = new Pessoa();
        pessoa.setId(2L);

        when(tarefaMock.getId()).thenReturn(tarefaId);
        when(Tarefa.findById(tarefaId)).thenReturn(tarefaMock);
        when(Pessoa.findById(pessoa.getId())).thenReturn(pessoa);
        when(tarefaMock.getPessoa()).thenReturn(null);
        when(tarefaMock.getDepartamento()).thenReturn(new Departamento());
        when(tarefaMock.getPessoa()).thenReturn(null);

        TarefaDTO tarefaDTO = tarefaService.alocarPessoa(tarefaId, pessoa);

        assertNotNull(tarefaDTO);
        verify(tarefaMock, times(1)).persist();
        assertEquals(pessoa, tarefaMock.getPessoa());
    }

    @Test
    void finalizarTarefa_FinalizaTarefaCorretamente() {
        Long tarefaId = 1L;

        when(Tarefa.findById(tarefaId)).thenReturn(tarefaMock);
        when(tarefaMock.getFinalizado()).thenReturn(false);

        Tarefa resultado = tarefaService.finalizarTarefa(tarefaId);

        assertNotNull(resultado);
        verify(tarefaMock, times(1)).persist();
        assertTrue(resultado.getFinalizado());
    }
}
