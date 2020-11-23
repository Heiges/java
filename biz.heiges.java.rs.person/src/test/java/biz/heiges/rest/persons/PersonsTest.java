package biz.heiges.rest.persons;

import static org.junit.Assert.assertEquals;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;

public class PersonsTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdownNow();
    }

    @Test
    public void testGetIt() {
        String responseMsg = target.path("persons").request().get(String.class);
        assertEquals("VieleHajos!", responseMsg);
    }

    @Test
    public void testGetAPersonWithoutId() {
        String responseMsg = target.path("persons/person").request().get(String.class);
        assertEquals("{\"familyName\":\"Heiges\",\"surname\":\"Hajo\"}", responseMsg);
    }

    @Test
    public void testGetAPersonWithId() {
        String responseMsg = target.path("persons/person/1").request().get(String.class);
        assertEquals("{\"familyName\":\"Heiges\",\"surname\":\"Hajo\"}", responseMsg);
    }

    @Test
    public void testGetAllPersons() {
        String responseMsg = target.path("persons/all").request().get(String.class);
        assertEquals("[{\"familyName\":\"Heiges\",\"surname\":\"Hajo\"},{\"familyName\":\"Heiges2\",\"surname\":\"Hajo2\"}]", responseMsg);
    }

    
}
