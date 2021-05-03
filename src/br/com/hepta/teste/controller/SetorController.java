package br.com.hepta.teste.controller;


import br.com.hepta.teste.entity.Setor;
import br.com.hepta.teste.service.SetorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/setor")
public class SetorController {

    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;

    private SetorService setorService;

    public SetorController() {
        setorService = new SetorService();
    }

    protected void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Path("/") @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodosSetores() {
        return setorService.listaSetores();
    }

    @Path("/") @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarSetor(Setor setor) {
        return setorService.adicionarSetor(setor);
    }

    @Path("/{id}") @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarSetor(@PathParam("id") Integer idSetor, Setor setor) {
        return setorService.atualizarSetor(idSetor, setor);
    }

    @Path("/{id}") @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response procurarSetorPorId(@PathParam("id") Integer idSetor) {
        return setorService.procurarSetorPorId(idSetor);
    }

    @Path("/{id}") @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletarSetor(@PathParam("id") Integer idSetor) {
       return setorService.deletarSetor(idSetor);
    }

}
