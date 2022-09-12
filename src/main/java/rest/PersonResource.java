package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.EMF_Creator;
import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//Todo Remove or change relevant parts before ACTUAL use
//@Path("person")
//public class PersonResource {
//
//    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
//    private static final PersonFacade FACADE =  PersonFacade.getPersonFacade(EMF);
//    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
//
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    public String demo() {
//        return "{\"msg\":\"Hello World\"}";
//    }
//
//    @GET
//    @Path("/all")
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response getAllPersons() {
//        return Response.ok().entity(GSON.toJson(FACADE.getAll())).build();
//    }

//    @POST
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_JSON})
//    public Response postExample(String input){
////        RenameMeDTO rmdto = GSON.fromJson(input, RenameMeDTO.class);
////        System.out.println(rmdto);
//        return Response.ok().entity().build();
//    }
//}
