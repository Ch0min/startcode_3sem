package datafacades;

import dtos.PersonDTO;
import entities.Person;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;

//import errorhandling.PersonNotFoundException;
import errorhandling.PersonNotFoundException;
import utils.EMF_Creator;

public class PersonFacade {

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

    public PersonDTO create(PersonDTO personDTO) {
        Person personEntity = new Person(personDTO.getName(), personDTO.getAge());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(personEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(personEntity);
    }

    public PersonDTO update(PersonDTO personDTO) {
        EntityManager em = getEntityManager();
        Person fromDB = em.find(Person.class, personDTO.getId());
        if (fromDB == null)
            throw new EntityNotFoundException("No such Person with id: " + personDTO.getId());

        Person personEntity = new Person(personDTO.getId(), personDTO.getName(), personDTO.getAge());
        try {
            em.getTransaction().begin();
            em.merge(personEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(personEntity);
    }

    public PersonDTO getById(long id) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, id);
        if (person == null)
            throw new PersonNotFoundException("The Person entity with ID: "+id+" Was not found");
        return new PersonDTO(person);
    }

    public List<PersonDTO> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> persons = query.getResultList();
        return PersonDTO.getDtos(persons);
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade pf = getPersonFacade(emf);
//        pf.getAll().forEach(dto -> System.out.println(dto));
        pf.update(new PersonDTO(6, "Fido", 3));
    }

}
