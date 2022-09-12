package facades;

import dtos.EmployeeDTO;
import entities.Employee;
import entities.RenameMe;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@Disabled
public class EmployeeFacadeTest {

    private static EntityManagerFactory emf;
    private static EmployeeFacade facade;

    public EmployeeFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = EmployeeFacade.getEmployeeFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Employee.deleteAllRows").executeUpdate();
            em.persist(new EmployeeDTO(new Employee("Mark", "Zinniavej 10", 10000)));
            em.persist(new EmployeeDTO(new Employee("Nick", "Lufthavnsparken 7", 500)));
            em.persist(new EmployeeDTO(new Employee("Cecilie", "Julius Andersensvej 4", 40000)));
            em.persist(new EmployeeDTO(new Employee("Fido", "Haven 7", 100)));

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
    public void testGetAllEmployees() throws Exception {
        assertEquals(4, facade.getAllEmployees().size(), "Expects 4 employees");
    }
    







}
