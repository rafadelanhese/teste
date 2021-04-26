package br.com.hepta.teste.interfacedao;

import br.com.hepta.teste.entity.Funcionario;
import br.com.hepta.teste.entity.Setor;

import java.util.List;

public interface IFuncionarioDAO {

    public void fecharConexao();
    public List<Funcionario> listarTodos();
    public boolean criar(Funcionario funcionario);
    public boolean atualizar(Funcionario funcionario);
    public Funcionario procurarPorId(int id);
    public Funcionario procurarPorNome(String nome);
    public boolean deletar(int id);
}
