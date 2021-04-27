package br.com.hepta.teste.service;

import br.com.hepta.teste.dao.FuncionarioDAO;
import br.com.hepta.teste.dao.SetorDAO;
import br.com.hepta.teste.entity.Funcionario;
import br.com.hepta.teste.entity.Setor;

import javax.ws.rs.core.Response;
import java.util.List;

public class FuncionarioService {

    private FuncionarioDAO funcionarioDAO;

    public FuncionarioService() {
        this.funcionarioDAO = new FuncionarioDAO();
    }

    public Response listarFuncionarios(){
        List<Funcionario> listaFuncionarios = funcionarioDAO.listarTodos();

        if(listaFuncionarios.isEmpty() || listaFuncionarios == null){
            return Response.status(Response.Status.BAD_REQUEST).entity("Não foi possível listar funcionários").build();
        }else{
            return Response.status(Response.Status.OK).entity(listaFuncionarios).build();
        }
    }

    public Response adicionarFuncionario(Funcionario funcionario){
        boolean funcionarioCriado = funcionarioDAO.criar(funcionario);

        if(funcionarioCriado){
            return Response.status(Response.Status.CREATED).entity("Funcionário foi adicionado com sucesso").build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("Falha ao adicionar funcionário").build();
        }
    }

    public Response atualizarSetor(int idFuncionario, Funcionario funcionario){
        Response response;
        Funcionario funcionarioAtualizar = funcionarioDAO.procurarPorId(idFuncionario);

        if(funcionarioAtualizar == null){
            response = Response.status(Response.Status.NOT_FOUND).entity("Funcionário não encontrado").build();
        }else{
            funcionarioAtualizar.setSetor(funcionario.getSetor());
            funcionarioAtualizar.setSalario(funcionario.getSalario());
            funcionarioAtualizar.setNome(funcionario.getNome());
            funcionarioAtualizar.setIdade(funcionario.getIdade());
            funcionarioAtualizar.setEmail(funcionario.getEmail());

            boolean funcionarioAtualizado = funcionarioDAO.atualizar(funcionarioAtualizar);

            if(funcionarioAtualizado){
                response =  Response.status(Response.Status.OK).entity(String.format("Funcionário: %s foi atualizado com sucesso", funcionarioAtualizar.getNome())).build();
            }else{
                response =  Response.status(Response.Status.BAD_REQUEST).entity("Funcionário não encontrado").build();
            }
        }

        return response;
    }

    public Response deletarFuncionario(int idFuncionario){
        boolean funcionarioDeletado = funcionarioDAO.deletar(idFuncionario);

        if(funcionarioDeletado){
            return Response.status(Response.Status.OK).entity("Funcionário foi deletado com sucesso").build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).entity("Funcionário não encontrado").build();
        }
    }
}
