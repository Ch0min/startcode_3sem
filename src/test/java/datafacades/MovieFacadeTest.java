package datafacades;

import entities.Movie;
import errorhandling.EntityNotFoundException;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
public class MovieFacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;
    Movie m1, m2, m3;

    public MovieFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = MovieFacade.getMovieFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Movie.deleteAllRows").executeUpdate();
            m1 = new Movie(2014, "Edge of Tommorow");
            m2 = new Movie(2011, "Source Code");
            m3 = new Movie(2010, "Inception");
            em.persist(m1);
            em.persist(m2);
            em.persist(m3);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    void create() {
        System.out.println("Testing create(Movie m)");
        Movie m = new Movie(2000,"TestMovie");
        m.setId(4);
        Movie expected = m;
        Movie actual = facade.create(m);
        assertEquals(expected, actual);
    }

    @Test
    void getById() throws EntityNotFoundException {
        System.out.println("Testing getById(id)");
        Movie expected = m1;
        Movie actual = facade.getById(m1.getId());
        assertEquals(expected, actual);
    }

    @Test
    void getAll() {
        System.out.println("Testing getAll()");
        int expected = 3;
        int actual = facade.getAll().size();
        assertEquals(expected,actual);
    }

    @Test
    void update() throws EntityNotFoundException {
        System.out.println("Testing Update(Movie m)");
        m2.setYear(1900);
        Movie expected = m2;
        Movie actual = facade.update(m2);
        assertEquals(expected, actual);
    }

    @Test
    void delete() throws EntityNotFoundException {
        System.out.println("Testing delete(id)");
        Movie m = facade.delete(m1.getId());
        int expected = 2;
        int actual = facade.getAll().size();
        assertEquals(expected, actual);
        assertEquals(m, m1);
    }

}





