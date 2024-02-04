//package org.perinity;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.perinity.model.Pessoa;
//import org.perinity.model.DTO.PessoaDTO;
//import org.perinity.resource.PessoaResource;
//
//import io.quarkus.test.InjectMock;
//import io.quarkus.test.junit.QuarkusTest;
//import jakarta.ws.rs.core.Response;
//
//@QuarkusTest
//public class PessoaResourceTest {
//
//    @InjectMock
//    PessoaResource pessoaResource;
//
//    @Test
//    public void testAdicionarPessoa() {
//        Pessoa pessoa = new Pessoa();
//        when(Pessoa.persist(any())).thenReturn(pessoa);
//
//        Response response = pessoaResource.adicionarPessoa(pessoa);
//
//        assertEquals(201, response.getStatus());
//        verify(Pessoa, times(1)).persist(pessoa);
//    }
//
//    @Test
//    public void testAlterarPessoa() {
//        Pessoa pessoa = new Pessoa();
//        when(Pessoa.findById(anyLong())).thenReturn(pessoa);
//
//        Response response = pessoaResource.alterarPessoa(1L, pessoa);
//
//        assertEquals(200, response.getStatus());
//        verify(Pessoa, times(1)).persist(pessoa);
//    }
//
//    @Test
//    public void testRemoverPessoa() {
//        Pessoa pessoa = new Pessoa();
//        when(Pessoa.findById(anyLong())).thenReturn(pessoa);
//
//        Response response = pessoaResource.removerPessoa(1L);
//
//        assertEquals(204, response.getStatus());
//        verify(Pessoa, times(1)).delete(pessoa);
//    }
//
//    @Test
//    public void testListarPessoas() {
//        List<Pessoa> pessoas = Arrays.asList(new Pessoa(), new Pessoa());
//        when(Pessoa.listAll()).thenReturn(pessoas);
//
//        List<PessoaDTO> result = pessoaResource.listarPessoas();
//
//        assertEquals(pessoas.size(), result.size());
//        verify(Pessoa, times(1)).listAll();
//    }
//}
