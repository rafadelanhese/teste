package test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
        String nomeSetorProcurado = "Teste 3";
        Setor setorEsperado =  dao.procurarPorNome(nomeSetorProcurado);
    
        assertEquals(nomeSetorProcurado, setorEsperado.getNome());
    }

    @Test
    public void atualizarTest(){
        Setor setorAtualizado = new Setor(2, "Setor Atualizado");
        boolean atualizouSetor = dao.atualizar(setorAtualizado);    
    
        assertTrue(atualizouSetor);      
    }


    @Test
    public void listarTodosTest(){
        int qntdeElementosEsperados = 5;
        List<Setor> listaSetorEsperada = dao.listarTodos();

        assertTrue(!listaSetorEsperada.isEmpty());                
        assertTrue(listaSetorEsperada.size() == qntdeElementosEsperados);        
    }

    @Test
    public void deletarTest(){
        int idDeletar = 5;       
        boolean setorDeletado = dao.deletar(idDeletar);

        assertTrue(setorDeletado);                       
    }        
}
