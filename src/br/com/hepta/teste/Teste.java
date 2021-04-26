package br.com.hepta.teste;

import java.util.List;
import java.util.stream.Stream;

import br.com.hepta.teste.dados.SetorDados;
import br.com.hepta.teste.dao.SetorDAO;
import br.com.hepta.teste.entity.Setor;

public class Teste {

    public static void main(String[] args) {

        SetorDados setorDados = new SetorDados();

        setorDados.criarSetor();
        setorDados.listagem();
        setorDados.procurarPorId();
        setorDados.procurarPorNome();
        setorDados.fecharConexao();
    }


    
}
