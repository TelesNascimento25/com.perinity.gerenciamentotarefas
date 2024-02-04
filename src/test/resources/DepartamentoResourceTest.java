package org.perinity.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.com.perinity.service.DepartamentoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.perinity.model.Departamento;

import jakarta.ws.rs.WebApplicationException;

class DepartamentoServiceTest {

    @InjectMocks
    private DepartamentoService departamentoService;

    @Mock
    private Departamento departamentoMock;

    @Test
    void adicionarDepartamento_DeveAdicionarDepartamentoCorretamente() {
        when(departamentoMock.getId()).thenReturn(null);

        Departamento resultado = departamentoService.adicionarDepartamento(departamentoMock);

        assertNotNull(resultado);
        verify(departamentoMock, times(1)).persist();
    }

    @Test
    void adicionarDepartamento_ComIdExistente_DeveLancarExcecao() {
        when(departamentoMock.getId()).thenReturn(1L);

        assertThrows(WebApplicationException.class, () -> {
            departamentoService.adicionarDepartamento(departamentoMock);
        });
    }

    @Test
    void alterarDepartamento_DeveAlterarDepartamentoCorretamente() {
        Long id = 1L;
        when(departamentoMock.getId()).thenReturn(id);
        when(Departamento.findById(id)).thenReturn(departamentoMock);

        Departamento novoDepartamento = new Departamento();
        novoDepartamento.setTitulo("Novo Título");

        Departamento resultado = departamentoService.alterarDepartamento(id, novoDepartamento);

        assertNotNull(resultado);
        assertEquals("Novo Título", resultado.getTitulo());
        verify(departamentoMock, times(1)).persist();
    }

    @Test
    void alterarDepartamento_ComDepartamentoInexistente_DeveLancarExcecao() {
        Long id = 1L;
        when(departamentoMock.getId()).thenReturn(id);
        when(Departamento.findById(id)).thenReturn(null);

        assertThrows(WebApplicationException.class, () -> {
            departamentoService.alterarDepartamento(id, departamentoMock);
        });
    }

}
