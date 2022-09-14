package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
import errorhandling.PersonNotFoundException;
import utils.EMF_Creator;
import datafacades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final PersonFacade FACADE =  PersonFacade.getPersonFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

//    @Path("/{username}")
//    @GET
//    @Produces("text/plain")
//    public String getUser(@PathParam("username") String userName) {
//        return "Hello " + userName;
//    }

    @Path("/testexception")
    @GET
    @Produces("text/plain")
    public String throwException() throws Exception {
        throw new Exception("My exception");
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getPerson(@PathParam("id") long id) throws PersonNotFoundException {
        return Response.ok().entity(GSON.toJson(FACADE.getById(id))).build();
    }

    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllPersons() {
        return Response.ok().entity(GSON.toJson(FACADE.getAll())).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createPerson(String jsonInput) {
        PersonDTO person = GSON.fromJson(jsonInput, PersonDTO.class);
        PersonDTO returned = FACADE.create(person);
        return Response.ok().entity(GSON.toJson(returned)).build();
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response updatePerson(@PathParam("id") long id, String personInput) {
        PersonDTO personDTO = GSON.fromJson(personInput, PersonDTO.class);
        personDTO.setId(id);
        PersonDTO returned = FACADE.update(personDTO);
        return Response.ok().entity(GSON.toJson(returned)).build();
    }
}

















