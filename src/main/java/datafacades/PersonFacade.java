package datafacades;

import entities.Address;
import entities.Person;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;

//import errorhandling.PersonNotFoundException;
import errorhandling.PersonNotFoundException;
import utils.EMF_Creator;

// LINK EXERCISE: https://docs.google.com/document/d/1-aFHS74YTg4xv6thEbpQPo0-LFw8c3YYBtuu9eRHayU/edit#

public class PersonFacade implements IDataFacade<Person> {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {
    }

    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Person create(Person person) {
        Person personEntity = new Person(person.getFname(), person.getLname(), person.getPhone());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(personEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return personEntity;
    }

    @Override
    public Person update(Person person) {
        EntityManager em = getEntityManager();
        if (person.getId() == 0)
            throw new EntityNotFoundException("No such Person with id: " + person.getId());
        em.getTransaction().begin();
        Person p = em.merge(person);
        em.getTransaction().commit();
        return p;
    }

    @Override
    public Person getById(int id) throws EntityNotFoundException {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, id);
        if (person == null)
            throw new EntityNotFoundException("The Person entity with ID: " + id + " Was not found");
        return person;
    }

    public List<Person> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> persons = query.getResultList();
        return persons;
    }

    public Person delete(int id) throws EntityNotFoundException {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, id);
        if (person == null) {
            throw new EntityNotFoundException("No such Person exist with the id " + id);
        }
        try {
            em.getTransaction().begin();
            em.remove(person);
            em.getTransaction().commit();
            return person;
        } finally {
            em.close();
        }
    }

    public Address create(Address address) {
        Address addressEntity = new Address(address.getStreet(), address.getCity(), address.getZip());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(addressEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return addressEntity;
    }

/* ***     ONE TO ONE      *** -change: Person, Address, PersonDTO, PersonFacade */
    public Person assignAddressToPerson(int addressId, int personId) {
        EntityManager em = emf.createEntityManager();
        Address address = em.find(Address.class, addressId);
        Person person = em.find(Person.class, personId);
        em.getTransaction().begin();
        person.setAddress(address);
        em.getTransaction().commit();
        em.close();
        return person;

    }


    public static void main(String[] args) throws PersonNotFoundException {
        emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade pf = getPersonFacade(emf);
//        pf.getAllPersons().forEach(dto -> System.out.println(dto));
//        System.out.println(pf.getPerson(1));
//        pf.editPerson(new PersonDTO(4, "Fido", "Le", "81723645"));
    }

}
