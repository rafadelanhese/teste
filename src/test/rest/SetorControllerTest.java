package test.rest;

import br.com.hepta.teste.entity.Setor;
import br.com.hepta.teste.controller.SetorController;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SetorControllerTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(SetorController.class);
    }

    @Test
    public void t1_GetListaSetoresTest(){
        Response response = target("/setor").request().get();
        String json = target("/setor").request().get(String.class);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertTrue(json.contains("Teste 1"));
        assertTrue(json.contains("Teste 3"));
        assertTrue(json.contains("Teste 4"));
    }

    @Test
    public void t2_CriarSetorTest(){
       Setor setor = new Setor("Setor Camada de Teste");

       Response response = target("/setor").request(MediaType.APPLICATION_JSON_TYPE)
               .post(Entity.entity(setor, MediaType.APPLICATION_JSON_TYPE));

       assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
       assertEquals("Setor foi adicinado com sucesso", response.readEntity(String.class));
    }

    @Test
    public void t3_AtualizarSetorTest(){
        Setor setor = new Setor("Setor Atualizar 2");

        Response response = target("/setor/2").request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.entity(setor, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Setor foi atualizado com sucesso", response.readEntity(String.class));
    }

    @Test
    public void t4_ProcurarSetorPorIdTest(){
        Setor setor = target("/setor/1").request().get(Setor.class);

        assertEquals("Teste 1", setor.getNome());
    }

    @Test
    public void t5_DeleteSetorTest(){
        Response response = target("/setor/7").request().delete();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Setor foi deletado com sucesso", response.readEntity(String.class));
    }
}
