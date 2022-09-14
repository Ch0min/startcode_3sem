package facades;

import dtos.MovieDTO;
import dtos.PersonDTO;
import entities.Movie;
import errorhandling.MovieNotFoundException;
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
    void testGetMovieByID() throws MovieNotFoundException {
        String expected = "Edge of Tomorrow";
        String actual = facade.getMovie(1).getTitle();
        assertEquals(expected, actual);

    }

    @Test
    void updateMovie() throws MovieNotFoundException {
        System.out.println("Testing updateMovie(MovieDTO m)");
        facade.updateMovie(new MovieDTO(2, 2011, "Source CodeCODE"));
        String actual = facade.getMovie(2).getTitle();
        String expected = "Source CodeCODE";
        assertEquals(expected, actual);
    }


    @Test
    void testGetAllMovies() {
        int expected = 3;
        int actual = facade.getAllMovies().size();
        assertEquals(expected, actual);
    }


//    @Test
//    void testCreateMovie() {
//        System.out.println("Testing createMovie(MovieDTO movieDTO)");
//        MovieDTO m = new MovieDTO(2000, "TestMovie");
//        MovieDTO expected = m;
//        MovieDTO actual = facade.createMovie(m);;
//        assertEquals(expected, actual);


        // Lærer eksempel
//        MovieDTO m = new MovieDTO(2000, "TestMovie");
//        MovieDTO expected = m;
//        MovieDTO actual = facade.createMovie(m);
//        assertEquals(expected, actual);

//    }


    @Test
    void deleteMovie() throws MovieNotFoundException {
        System.out.println("Testing deleteMovie(id)");
        Movie m = facade.deleteMovie(m1.getId());
        int expected = 2;
        int actual = facade.getAllMovies().size();
        assertEquals(expected, actual);
        assertEquals(m, m1);

    }



}





