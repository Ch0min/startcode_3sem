package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.MovieDTO;
import dtos.PersonDTO;
import entities.Movie;
import entities.Person;
import errorhandling.MovieNotFoundException;
import errorhandling.PersonNotFoundException;
import facades.MovieFacade;
import utils.EMF_Creator;
import facades.PersonFacade;

import javax.ejb.Remove;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("movie")
public class MovieResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final MovieFacade FACADE =  MovieFacade.getMovieFacade(EMF);
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

    @Path("/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllMovies() {
        return Response.ok().entity(GSON.toJson(FACADE.getAllMovies())).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getMovie(@PathParam("id") long id) throws MovieNotFoundException {
        return Response.ok().entity(GSON.toJson(FACADE.getMovie(id))).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createMovie(String jsonInput) {
        MovieDTO movie = GSON.fromJson(jsonInput, MovieDTO.class);
        MovieDTO returned = FACADE.createMovie(movie);
        return Response.ok().entity(GSON.toJson(returned)).build();
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response updateMovie(@PathParam("id") long id, String movieInput) {
        MovieDTO movieDTO = GSON.fromJson(movieInput, MovieDTO.class);
        movieDTO.setId(id);
        MovieDTO returned = FACADE.updateMovie(movieDTO);
        return Response.ok().entity(GSON.toJson(returned)).build();
    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response deleteMovie(@PathParam("id") long id) throws MovieNotFoundException {
        MovieDTO deleted = new MovieDTO(FACADE.deleteMovie(id));
        return Response.ok().entity(GSON.toJson(deleted)).build();
    }
}







