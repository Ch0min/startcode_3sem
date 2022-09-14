/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.EmployeeDTO;
import dtos.MovieDTO;
import dtos.PersonDTO;
import dtos.RenameMeDTO;
import entities.Employee;
import entities.Movie;
import entities.Person;
import entities.RenameMe;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
//        PersonFacade pf = PersonFacade.getPersonFacade(emf);
//        pf.create(new PersonDTO(new Person("Hans", 43)));
//        pf.create(new PersonDTO(new Person("Hanne", 21)));
//        pf.create(new PersonDTO(new Person("Hassan", 3)));

//        EmployeeFacade ef = EmployeeFacade.getEmployeeFacade(emf);
//        ef.create(new EmployeeDTO(new Employee("Mark", "Zinniavej 10", 10000)));
//        ef.create(new EmployeeDTO(new Employee("Nick", "Lufthavnsparken 7", 500)));
//        ef.create(new EmployeeDTO(new Employee("Cecilie", "Julius Andersensvej 4", 40000)));
//        ef.create(new EmployeeDTO(new Employee("Fido", "Haven 7", 100)));

        MovieFacade mf = MovieFacade.getMovieFacade(emf);
        mf.createMovie(new MovieDTO(new Movie(2014, "Edge of Tomorrow")));
        mf.createMovie(new MovieDTO(new Movie(2011, "Source Code")));
        mf.createMovie(new MovieDTO(new Movie(2010, "Inception")));



    }

    public static void main(String[] args) {
        populate();
    }
}
