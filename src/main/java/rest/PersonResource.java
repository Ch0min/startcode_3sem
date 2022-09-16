package rest;

import businessfacades.PersonDTOFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.PersonDTO;
<<<<<<< HEAD
import errorhandling.EntityNotFoundException;
import datafacades.IDataFacade;

=======
import errorhandling.PersonNotFoundException;
import utils.EMF_Creator;
import datafacades.PersonFacade;
import javax.persistence.EntityManagerFactory;
>>>>>>> d1dc78caf6e152eee2a33021df97f71d8889899c
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

<<<<<<< HEAD
@Path("person")
public class PersonResource {

    private static final IDataFacade<PersonDTO> FACADE =  PersonDTOFacade.getFacade();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllPersons() {
        return Response.ok().entity(GSON.toJson(FACADE.getAll())).build();
=======
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
>>>>>>> d1dc78caf6e152eee2a33021df97f71d8889899c
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
<<<<<<< HEAD
    public Response getPersonById(@PathParam("id") int id) throws EntityNotFoundException {
        return Response.ok().entity(GSON.toJson(FACADE.getById(id))).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createPerson(String content) {
        PersonDTO pdto = GSON.fromJson(content, PersonDTO.class);
        PersonDTO newPdto = FACADE.create(pdto);
        return Response.ok().entity(GSON.toJson(newPdto)).build();
=======
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
>>>>>>> d1dc78caf6e152eee2a33021df97f71d8889899c
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
<<<<<<< HEAD
    public Response updatePerson(@PathParam("id") int id, String personInput) throws EntityNotFoundException {
=======
    public Response updatePerson(@PathParam("id") long id, String personInput) {
>>>>>>> d1dc78caf6e152eee2a33021df97f71d8889899c
        PersonDTO personDTO = GSON.fromJson(personInput, PersonDTO.class);
        personDTO.setId(id);
        PersonDTO returned = FACADE.update(personDTO);
        return Response.ok().entity(GSON.toJson(returned)).build();
    }
<<<<<<< HEAD

    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response deletePerson(@PathParam("id") int id) throws EntityNotFoundException {
        PersonDTO deleted = FACADE.delete(id);
        return Response.ok().entity(GSON.toJson(deleted)).build();
    }
=======
>>>>>>> d1dc78caf6e152eee2a33021df97f71d8889899c
}

















