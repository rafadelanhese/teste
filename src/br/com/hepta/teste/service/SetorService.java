package br.com.hepta.teste.service;

import br.com.hepta.teste.dao.SetorDAO;
import br.com.hepta.teste.entity.Setor;

import javax.ws.rs.core.Response;
import java.util.List;


public class SetorService {

    private SetorDAO setorDAO;

    public SetorService() {
        this.setorDAO = new SetorDAO();
    }

    public Response listaSetores(){
        List<Setor> listaSetores = setorDAO.listarTodos();

        if(listaSetores.isEmpty() || listaSetores == null){
            return Response.status(Response.Status.BAD_REQUEST).entity("Não foi possível listar setores").build();
        }else{
            return Response.status(Response.Status.OK).entity(listaSetores).build();
        }
    }

    public Response adicionarSetor(Setor setor){
        boolean setorCriado = setorDAO.criar(setor);

        if(setorCriado){
            return Response.status(Response.Status.CREATED).entity("Setor foi adicinado com sucesso").build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("Falha ao adicionar setor").build();
        }
    }

    public Response atualizarSetor(int idSetor, Setor setor){
        Setor setorAtualizar = setorDAO.procurarPorId(idSetor);

        setorAtualizar.setNome(setor.getNome());

        boolean setorAtualizado = setorDAO.atualizar(setorAtualizar);

        if(setorAtualizado){
            return Response.status(Response.Status.OK).entity("Setor foi atualizado com sucesso").build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).entity("Setor não encontrado").build();
        }
    }

    public Response deletarSetor(int idSetor){
        boolean setorDeletado = setorDAO.deletar(idSetor);

        if(setorDeletado){
            return Response.status(Response.Status.OK).entity("Setor foi deletado com sucesso").build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).entity("Setor não encontrado").build();
        }
    }
}
