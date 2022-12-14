package rest;

import errorhandling.EntityNotFoundException;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(errorhandling.EntityNotFoundExceptionMapper.class);
        resources.add(errorhandling.PersonNotFoundExceptionMapper.class);
        resources.add(errorhandling.GenericExceptionMapper.class);
        resources.add(org.glassfish.jersey.jsonb.internal.JsonBindingProvider.class);
        resources.add(org.glassfish.jersey.server.wadl.internal.WadlResource.class);
<<<<<<< HEAD
        resources.add(rest.PersonResource.class);
=======
        resources.add(rest.MovieResource.class);
        resources.add(rest.EmployeeResource.class);
        resources.add(rest.PersonResource.class);

>>>>>>> d1dc78caf6e152eee2a33021df97f71d8889899c
    }
    
}
