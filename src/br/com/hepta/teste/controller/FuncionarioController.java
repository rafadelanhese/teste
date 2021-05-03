package br.com.hepta.teste.controller;

import br.com.hepta.teste.entity.Funcionario;
import br.com.hepta.teste.service.FuncionarioService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/funcionario")
public class FuncionarioController {

    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;

    private FuncionarioService funcionarioService;

    public FuncionarioController() {
        funcionarioService = new FuncionarioService();
    }

    protected void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Path("/") @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodosFuncionarios() {
        return funcionarioService.listarFuncionarios();
    }

    @Path("/{id}") @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response procurarFuncionarioPorId(@PathParam("id") Integer idFuncionario) {
        return funcionarioService.procurarFuncionarioPorID(idFuncionario);
    }

    @Path("/") @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarFuncionario(Funcionario funcionario) {
        return funcionarioService.adicionarFuncionario(funcionario);
    }

    @Path("/{id}") @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarFuncionario(@PathParam("id") Integer idFuncionario, Funcionario funcionario) {
        return funcionarioService.atualizarFuncionario(idFuncionario, funcionario);
    }

    @Path("/{id}") @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarSetor(@PathParam("id") Integer idFuncionario) {
        return funcionarioService.deletarFuncionario(idFuncionario);
    }
}
