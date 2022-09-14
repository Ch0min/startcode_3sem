package rest;

import businessfacades.MovieDTOFacade;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.MovieDTO;
import errorhandling.EntityNotFoundException;
import datafacades.IDataFacade;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
@Path("movie")
public class MovieResource {

    private static final IDataFacade<MovieDTO> FACADE =  MovieDTOFacade.getFacade();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        return Response.ok().entity(GSON.toJson(FACADE.getAll())).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") int id) throws EntityNotFoundException {
        return Response.ok().entity(GSON.toJson(FACADE.getById(id))).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(String content) {
        MovieDTO mdto = GSON.fromJson(content, MovieDTO.class);
        MovieDTO newMdto = FACADE.create(mdto);
        return Response.ok().entity(GSON.toJson(newMdto)).build();
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response update(@PathParam("id") int id, String movieInput) throws EntityNotFoundException {
        MovieDTO movieDTO = GSON.fromJson(movieInput, MovieDTO.class);
        movieDTO.setId(id);
        MovieDTO updated = FACADE.update(movieDTO);
        return Response.ok().entity(GSON.toJson(updated)).build();
    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{id}")
    public Response delete(@PathParam("id") int id) throws EntityNotFoundException {
        MovieDTO deleted = FACADE.delete(id);
        return Response.ok().entity(GSON.toJson(deleted)).build();
    }
}







