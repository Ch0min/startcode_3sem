package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import entities.Person;
import io.restassured.http.ContentType;
import utils.EMF_Creator;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.parsing.Parser;
import java.net.URI;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


//@Disabled
public class PersonResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    private static Person p1, p2;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    // Setup the DataBase (used by the test-server and this test) in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        p1 = new Person("Mark", "Chomin", "29842712");
        p2 = new Person("Nick", "Lundgaard", "12345678");

        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.persist(p1);
            em.persist(p2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        System.out.println("Testing is server UP");
        given().when().get("/person").then().statusCode(200);
    }

    @Test
    public void testLogRequest() {
        System.out.println("Testing logging request details");
        given().log().all()
                .when().get("/person")
                .then().statusCode(200);
    }

    @Test
    public void testLogResponse() {
        System.out.println("Testing logging response details");
        given()
                .when().get("/person")
                .then().log().body().statusCode(200);
    }

    @Test
    public void testGetById()  {
        given()
                .contentType(ContentType.JSON)
                .get("/person/{id}", p1.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK_200.getStatusCode())
                .body("id", equalTo(p1.getId()))
                .body("fname", equalTo(p1.getFname()))
                .body("lname", equalTo(p1.getLname()))
                .body("phone", equalTo(p1.getPhone()));

    }

    @Test
    public void getAllPersons() throws Exception {
        List<PersonDTO> movieDTOs;

        movieDTOs = given()
                .contentType("application/json")
                .when()
                .get("/person")
                .then()
                .extract().body().jsonPath().getList("", PersonDTO.class);

        PersonDTO p1DTO = new PersonDTO(p1);
        PersonDTO p2DTO = new PersonDTO(p2);
        assertThat(movieDTOs, containsInAnyOrder(p1DTO, p2DTO));

    }


    @Test
    public void createTest() {
        Person m = new Person("Figa","Lele", "12341234");
        PersonDTO pdto = new PersonDTO(m);
        String requestBody = GSON.toJson(pdto);

        given()
                .header("Content-type", ContentType.JSON)
                .and()
                .body(requestBody)
                .when()
                .post("/person")
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", notNullValue())
                .body("fname", equalTo("Figa"))
                .body("lname", equalTo("Lele"))
                .body("phone", equalTo("12341234"));
    }

    @Test
    public void updateTest() {
        p2.setFname("MarksNewName");
        p2.setLname("LastNewName");
        p2.setPhone("12344321");
        PersonDTO pdto = new PersonDTO(p2);
        String requestBody = GSON.toJson(pdto);

        given()
                .header("Content-type", ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/person/" + p2.getId())
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(p2.getId()))
                .body("fname", equalTo("MarksNewName"))
                .body("lname", equalTo("LastNewName"))
                .body("phone", equalTo("12344321"));
    }

    @Test
    public void testDeleteMovie() {
        given()
                .contentType(ContentType.JSON)
                .pathParam("id", p2.getId())
                .delete("/person/{id}")
                .then()
                .statusCode(200)
                .body("id",equalTo(p2.getId()));
    }
}
