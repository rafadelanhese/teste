package test.rest;

import br.com.hepta.teste.entity.Funcionario;
import br.com.hepta.teste.entity.Setor;
import br.com.hepta.teste.controller.FuncionarioController;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FuncionarioControllerTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(FuncionarioController.class);
    }

    @Test
    public void t1_GetListaFuncionariosTest(){
        Response response = target("/funcionario").request().get();
        String json = target("/funcionario").request().get(String.class);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertTrue(json.contains("Teste Atualização"));
    }

    @Test
    public void t2_CriarFuncionarioTest(){
        Funcionario funcionario = new Funcionario("Funcionario Caso de teste", new Setor(1, "Teste 1"), 3500.55, "gmail@gmail.com", 44);

        Response response = target("/funcionario").request(MediaType.APPLICATION_JSON_TYPE)
                                                .post(Entity.entity(funcionario, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals("Funcionário foi adicionado com sucesso", response.readEntity(String.class));
    }

    @Test
    public void t3_AtualizarSetorTest(){
        Funcionario funcionario = new Funcionario("Func. Caso de Teste Atualizado", new Setor(1, "Teste 1"), 3500.55, "gmail@gmail.com", 44);

        Response response = target("/funcionario/5").request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.entity(funcionario, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(String.format("Funcionário: %s foi atualizado com sucesso", funcionario.getNome()), response.readEntity(String.class));
    }

    @Test
    public void t4_ProcurarFuncionarioPorIdTest(){
        Funcionario funcionario = target("/funcionario/3").request().get(Funcionario.class);

        assertEquals("Teste Atualização", funcionario.getNome());
        assertEquals("gmail@gmail.com", funcionario.getEmail());
    }

    @Test
    public void t5_DeleteSetorTest(){
        Response response = target("/funcionario/5").request().delete();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Funcionário foi deletado com sucesso", response.readEntity(String.class));
    }
}
