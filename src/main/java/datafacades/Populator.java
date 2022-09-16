/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datafacades;

import javax.persistence.EntityManagerFactory;

import entities.Address;
import entities.Person;
import utils.EMF_Creator;


public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade pf = PersonFacade.getPersonFacade(emf);
//        pf.create(new Person("Mark", "Chomin", "29842712"));
//        pf.create(new Person("Nick", "Lundgaard", "12345678"));
//        pf.create(new Person("Cecilie", "Ravn", "8765431"));
//        pf.create(new Person("Fido", "Lele", "54637218"));

        // Create new address
//        pf.create(new Address("Zinniavej 10", "Kastrup", "2770"));
//        pf.create(new Address("Testvej 1", "Test", "0101"));


/* ***     ONE TO ONE      *** -change: Person, Address, PersonDTO, PersonFacade  */
        // Assign address to Person
//        Person p1 = pf.assignAddressToPerson(3, 3);

        // Delete Person aswell as its Address
//        Person p1 = pf.delete(3);



/* ***     ONE TO MANY     *** */



    }
    
    public static void main(String[] args) {
        populate();
    }
}
