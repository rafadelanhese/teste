package br.com.hepta.teste.dados;

import br.com.hepta.teste.dao.SetorDAO;
import br.com.hepta.teste.entity.Setor;

import java.util.List;
import java.util.stream.Stream;

public class SetorDados {

    private SetorDAO dao;
    private Setor setor;

    public SetorDados(){
        this.dao = new SetorDAO();
        this.setor = new Setor();
    }

    public void criarSetor(){
        setor.setNome("ATeste de criação");
        boolean criouSetor = dao.criar(setor);

        if(criouSetor)
            System.out.println("Setor criado com sucesso");
        else
            System.out.println("Falha na criação do setor");
    }

    public void listagem(){
        List<Setor> listaSetores = dao.listarTodos();

        if(listaSetores.isEmpty() || listaSetores == null){
            System.out.println("Não é possível lista os setores");
        }else{
            listaSetores.forEach(info -> System.out.println(String.format("ID: %d - Nome: %s", info.getId(), info.getNome())));
            //list p/ stream e filtra pelo que contém Teste
            System.out.println("Stream");
            Stream<Setor> stream = listaSetores.stream();
            stream.filter(st -> st.getNome().contains("Teste")).forEach(setor -> System.out.println(String.format("ID: %d - Nome: %s", setor.getId(), setor.getNome())));
        }
    }

    public void atualizar(){
        Setor setorAtualizado = new Setor(17, "Atualizado Teste");
        dao.atualizar(setorAtualizado);
    }

    public void procurarPorId(){
        int idSetorProcurado = 1;
        setor = dao.procurarPorId(idSetorProcurado);
        if(setor != null)
            System.out.printf("Setor encontrado: %d - %s", setor.getId(), setor.getNome());
        else
            System.out.printf("Setor não encontrado");
    }

    public void procurarPorNome(){
        String nomeSetorProcurado = "Setor Atualizado";
        setor = dao.procurarPorNome(nomeSetorProcurado);
        if(setor != null)
            System.out.printf("Setor encontrado: %d - %s", setor.getId(), setor.getNome());
        else
            System.out.printf("Setor não encontrado");
    }

    public void deletar(){
        int idSetorDeletar = 20;
        dao.deletar(idSetorDeletar);
    }

    public void fecharConexao(){
        dao.fecharConexao();
    }
}
