/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datafacades;

import javax.persistence.EntityManagerFactory;

import dtos.PersonDTO;
import entities.Person;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade pf = PersonFacade.getPersonFacade(emf);
        pf.create(new Person("Mark", "Chomin", "29842712"));
        pf.create(new Person("Nick", "Lundgaard", "12345678"));
        pf.create(new Person("Cecilie", "Ravn", "8765431"));
        pf.create(new Person("Fido", "Lele", "54637218"));

    }
    
    public static void main(String[] args) {
        populate();
    }
}
