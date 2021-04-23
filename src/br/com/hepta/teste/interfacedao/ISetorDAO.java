package br.com.hepta.teste.interfacedao;

import java.util.List;

import br.com.hepta.teste.entity.Setor;

public interface ISetorDAO {

    public List<Setor> listarTodos();
    public boolean criar(Setor setor);
    public boolean atualizar(Setor setor);
    public Setor procurarPorId(int id);
    public Setor procurarPorNome(String nome);
    public boolean deletar(int id);
    
}
