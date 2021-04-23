package br.com.hepta.teste;

import java.util.List;
import java.util.stream.Stream;

import br.com.hepta.teste.dao.SetorDAO;
import br.com.hepta.teste.entity.Setor;

public class Teste {

    public static void main(String[] args) {    

        SetorDAO dao = new SetorDAO();
        Setor setor = new Setor();
        
        //criação
        setor.setNome("ATeste de criação");
        dao.criar(setor);

        //listagem
        //List<Setor> lista = dao.listarTodos();

        /*if(lista != null){
            lista.forEach(info -> System.out.println("ID: " + info.getId() + " - Nome: " + info.getNome()));
            //list p/ stream e filtra pelo que contém Teste
            System.out.println("Stream");
            Stream<Setor> stream = lista.stream();
            stream.filter(st -> st.getNome().contains("Teste")).forEach(s -> System.out.println("ID: " + s.getId() + " - Nome: " + s.getNome()));
        }*/
        
        //atualização        
        //dao.atualizar(new Setor(17, "Atualizado Teste"));

        //procura por ID        
        setor = dao.procurarPorId(1);
        if(setor != null)
            System.out.printf("Setor encontrado: %d - %s", setor.getId(), setor.getNome());
        else
            System.out.printf("Setor não encontrado");

        //deletar
        //dao.deletar(20);

    }
    
}
