package test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.hepta.teste.dao.SetorDAO;
import br.com.hepta.teste.entity.Setor;

public class SetorDAOTest{

    private SetorDAO dao;

    @Before
    public void init(){
        this.dao = new SetorDAO();    
    }

    @After
    public void encerrar(){
        this.dao.fecharConexao();
    }


    @Test
    public void criarTest(){
        Setor novoSetor = new Setor("Setor Caso de Teste");  
        boolean setorCriado = dao.criar(novoSetor);
        
        assertTrue(setorCriado);
    }

    @Test
    public void procurarPorIdTest(){
        int idSetorProcurado = 1;
        Setor setorEsperado =  dao.procurarPorId(idSetorProcurado);       

        assertTrue(setorEsperado.getId() == idSetorProcurado);        
    }

    @Test
    public void procurarPorNomeTest(){
        String nomeSetorProcurado = "Setor Caso de Teste";
        Setor setorEsperado =  dao.procurarPorNome(nomeSetorProcurado);
    
        assertEquals(nomeSetorProcurado, setorEsperado.getNome());
    }

    @Test
    public void atualizarTest(){
        int idSetorAtualizar = 1;
        Setor setorAtualizar = dao.procurarPorId(idSetorAtualizar);

        setorAtualizar.setNome("Setor Atualizado");

        boolean atualizouSetor = dao.atualizar(setorAtualizar);
    
        assertTrue(atualizouSetor);      
    }


    @Test
    public void listarTodosTest(){
        int qtdeElementosEsperados = 1;
        List<Setor> listaSetorEsperada = dao.listarTodos();

        assertTrue(!listaSetorEsperada.isEmpty());                
        assertTrue(listaSetorEsperada.size() == qtdeElementosEsperados);
    }

    @Test
    public void deletarTest(){
        int idDeletar = 1;
        boolean setorDeletado = dao.deletar(idDeletar);

        assertTrue(setorDeletado);                       
    }        
}
