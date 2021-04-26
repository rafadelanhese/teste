package test.dao;

import br.com.hepta.teste.dao.FuncionarioDAO;
import br.com.hepta.teste.dao.SetorDAO;
import br.com.hepta.teste.entity.Funcionario;
import br.com.hepta.teste.entity.Setor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FuncionarioDAOTest {

    private FuncionarioDAO dao;

    private SetorDAO setorDAO;

    @Before
    public void init(){
        this.dao = new FuncionarioDAO();
        this.setorDAO = new SetorDAO();
    }

    @After
    public void encerrar(){
        this.dao.fecharConexao();
        this.setorDAO.fecharConexao();
    }


    @Test
    public void criarTest(){
        int idSetorProcurado = 1;
        Setor setorFuncionario = setorDAO.procurarPorId(idSetorProcurado);
        Funcionario novoFuncionario = new Funcionario("Funcionário Teste", setorFuncionario, 2000.50, "email@email.com.br", 25 );
        boolean funcionarioCriado = dao.criar(novoFuncionario);

        assertTrue(funcionarioCriado);
    }

    @Test
    public void procurarPorIdTest(){
        int idFuncionarioProcurado = 1;
        Funcionario funcionarioEsperado =  dao.procurarPorId(idFuncionarioProcurado);

        assertTrue(funcionarioEsperado.getId() == idFuncionarioProcurado);
    }

    @Test
    public void procurarPorNomeTest(){
        String nomeFuncionarioProcurado = "Funcionário Teste";
        Funcionario funcionarioEsperado =  dao.procurarPorNome(nomeFuncionarioProcurado);

        assertEquals(nomeFuncionarioProcurado, funcionarioEsperado.getNome());
    }

    @Test
    public void atualizarTest(){
        Funcionario funcionarioAtualizado =  dao.procurarPorId(1);
        funcionarioAtualizado.setNome("Funcionário Atualizado");
        funcionarioAtualizado.setEmail("gmail@gmail.com");

        boolean atualizouFuncionario = dao.atualizar(funcionarioAtualizado);

        assertTrue(atualizouFuncionario);
    }


    @Test
    public void listarTodosTest(){
        int qtdeElementosEsperados = 1;
        List<Funcionario> listaFuncionarioEsperado = dao.listarTodos();

        assertTrue(!listaFuncionarioEsperado.isEmpty());
        assertTrue(listaFuncionarioEsperado.size() == qtdeElementosEsperados);
    }

    @Test
    public void deletarTest(){
        int idFuncionarioDeletar = 1;
        boolean funcionarioDeletado = dao.deletar(idFuncionarioDeletar);

        assertTrue(funcionarioDeletado);
    }
}
