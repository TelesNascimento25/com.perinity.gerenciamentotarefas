package org.perinity.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.com.perinity.service.PessoaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.perinity.model.Departamento;
import org.perinity.model.Pessoa;
import org.perinity.model.Tarefa;
import org.perinity.model.DTO.PessoaGastoDTO;
import org.perinity.model.DTO.PessoaListarDTO;
import org.perinity.model.DTO.PessoaRespostaDTO;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class PessoaServiceTest {

    @InjectMocks
    private PessoaService pessoaService;

    @Mock
    private Departamento departamentoMock;

    @Test
    void adicionarPessoa_DeveAdicionarPessoaCorretamente() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("João");
        pessoa.setDepartamento(departamentoMock);

        when(Departamento.findById(departamentoMock.getId())).thenReturn(departamentoMock);

        try {
            Pessoa resultado = pessoaService.adicionarPessoa(pessoa);

            assertNotNull(resultado);
            assertEquals(pessoa.getId(), resultado.getId());
            assertEquals(pessoa.getNome(), resultado.getNome());
            assertEquals(departamentoMock, resultado.getDepartamento());

        } catch (Exception e) {
            fail("Não era esperada nenhuma exceção, mas foi lançada: " + e.getClass().getSimpleName());
        }
    }

    @Test
    void alterarPessoa_DeveAlterarPessoaCorretamente() {
        Long id = 1L;
        Pessoa pessoaExistente = new Pessoa();
        pessoaExistente.setId(id);
        pessoaExistente.setNome("Maria");

        Pessoa pessoaNova = new Pessoa();
        pessoaNova.setId(id);
        pessoaNova.setNome("Ana");
        pessoaNova.setDepartamento(departamentoMock);

        when(Pessoa.findById(id)).thenReturn(pessoaExistente);
        when(Departamento.findById(departamentoMock.getId())).thenReturn(departamentoMock);

        PessoaRespostaDTO resposta = pessoaService.alterarPessoa(id, pessoaNova);

        assertNotNull(resposta);
        assertEquals(pessoaNova.getId(), resposta.getId());
        assertEquals(pessoaNova.getNome(), resposta.getNome());
        assertEquals(departamentoMock, pessoaExistente.getDepartamento());
    }

    @Test
    void removerPessoa_DeveRemoverPessoaCorretamente() {
        Long id = 1L;
        Pessoa pessoaExistente = new Pessoa();
        pessoaExistente.setId(id);
        pessoaExistente.setNome("João");

        when(Pessoa.findById(id)).thenReturn(pessoaExistente);

        String mensagem = pessoaService.removerPessoa(id);

        assertEquals("Pessoa deletada com sucesso!", mensagem);
        verify(pessoaExistente, times(1)).delete();
    }

    @Test
    void listarPessoas_DeveListarPessoasCorretamente() {
        List<Pessoa> pessoasMock = Arrays.asList(
                criarPessoa(1L, "João"),
                criarPessoa(2L, "Maria"),
                criarPessoa(3L, "José")
        );

        when(Pessoa.listAll()).thenReturn(Mockito.anyList());

        List<PessoaListarDTO> lista = pessoaService.listarPessoas();

        assertNotNull(lista);
        assertEquals(pessoasMock.size(), lista.size());

        for (int i = 0; i < pessoasMock.size(); i++) {
            PessoaListarDTO dto = lista.get(i);
            Pessoa pessoa = pessoasMock.get(i);

            assertEquals(pessoa.getId(), dto.getId());
            assertEquals(pessoa.getNome(), dto.getNome());
        }
    }

    @Test
    void buscarGastosPorNomeEPrazo_DeveRetornarGastosCorretamente() {
        String nome = "João";
        LocalDate prazoInicio = LocalDate.now().minusDays(10);
        LocalDate prazoFim = LocalDate.now();

        List<Pessoa> pessoasMock = Arrays.asList(
                criarPessoaComTarefas(1L, "João", criarTarefa(1L, "Tarefa1", prazoInicio, true, 120L)),
                criarPessoaComTarefas(2L, "Maria", criarTarefa(2L, "Tarefa2", prazoInicio, false, 90L)),
                criarPessoaComTarefas(3L, "José", criarTarefa(3L, "Tarefa3", prazoInicio.plusDays(5), true, 180L))
        );

        when(Pessoa.list("nome", nome)).thenReturn(Mockito.anyList());

        List<PessoaGastoDTO> lista = pessoaService.buscarGastosPorNomeEPrazo(nome, prazoInicio, prazoFim);

        assertNotNull(lista);
        assertEquals(2, lista.size());

        PessoaGastoDTO joaoDTO = lista.get(0);
        assertEquals("João", joaoDTO.getNome());
        assertEquals(prazoInicio, joaoDTO.getPeriodoInicio());
        assertEquals(prazoFim, joaoDTO.getPeriodoFim());
        assertEquals(2.0, joaoDTO.getMediaHoras()); 

        PessoaGastoDTO mariaDTO = lista.get(1);
        assertEquals("Maria", mariaDTO.getNome());
        assertEquals(prazoInicio, mariaDTO.getPeriodoInicio());
        assertEquals(prazoFim, mariaDTO.getPeriodoFim());
        assertEquals(1.5, mariaDTO.getMediaHoras());
    }

    private Pessoa criarPessoa(Long id, String nome) {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        pessoa.setNome(nome);
        return pessoa;
    }

    private Pessoa criarPessoaComTarefas(Long id, String nome, Tarefa... tarefas) {
        Pessoa pessoa = criarPessoa(id, nome);
        pessoa.setTarefas(Arrays.asList(tarefas));
        return pessoa;
    }

    private Tarefa criarTarefa(Long id, String descricao, LocalDate prazo, boolean finalizado, Long duracao) {
        Tarefa tarefa = new Tarefa();
        tarefa.setId(id);
        tarefa.setDescricao(descricao);
        tarefa.setPrazo(prazo);
        tarefa.setFinalizado(true);
        tarefa.setDuracao(duracao);
        return tarefa;
    }
}
