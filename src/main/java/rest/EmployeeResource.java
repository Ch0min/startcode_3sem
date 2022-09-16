package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import datafacades.EmployeeFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("employee")
public class EmployeeResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final EmployeeFacade FACADE =  EmployeeFacade.getEmployeeFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllEmployees() {
        return Response.ok().entity(GSON.toJson(FACADE.getAllEmployees())).build();
    }

    @GET
    @Path("/id")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getEmployeeId() {
        return Response.ok().entity(GSON.toJson(FACADE.getEmployeeById(1))).build();
    }

    @GET
    @Path("/highestpaid")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getHighestPaid() {
        return Response.ok().entity(GSON.toJson(FACADE.getEmployeesWithHighestSalaryFromTop())).build();
    }

    @GET
    @Path("/name")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getName() {
        return Response.ok().entity(GSON.toJson(FACADE.getEmployeeByName("Fido"))).build();
    }


//    @POST
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_JSON})
//    public Response postExample(String input){
////        RenameMeDTO rmdto = GSON.fromJson(input, RenameMeDTO.class);
////        System.out.println(rmdto);
//        return Response.ok().entity().build();
//    }
}
