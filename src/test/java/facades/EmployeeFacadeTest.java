package facades;

import dtos.EmployeeDTO;
import entities.Employee;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
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
            em.persist(new Employee("Mark", "Zinniavej 10", 10000));
            em.persist(new Employee("Nick", "Lufthavnsparken 7", 500));
            em.persist(new Employee("Cecilie", "Julius Andersensvej 4", 40000));
            em.persist(new Employee("Fido", "Haven 7", 100));

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
    public void testGetEmployeeByID() {
        String actual = facade.getEmployeeById(4).getName();
        String expected = "Nick";
        assertEquals(expected, actual);

    }

//    @Test
//    public void testGetEmployeeByName() {
//        Employee employee = (Employee) facade.getEmployeeByName("Mark");
//        String actual = employee.getName();
//        String expected = "Mark";
//        assertEquals(expected, actual);
//
//    }

    @Test
    public void testGetAllEmployees() throws Exception {
        int actual = facade.getAllEmployees().size();
        int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void testGetEmployeesWithHighestSalary() {
        String actual = facade.getEmployeesWithHighestSalaryFromTop().get(0).getName();
        String expected = "Cecilie";
        assertEquals(expected, actual);

    }

    @Test
    public void testCreateEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO("Chomin", "Zin 7", 10);
        facade.create(employeeDTO);
        String actual = facade.getEmployeeById(5).getName();
        String expected = "Chomin";
        assertEquals(expected, actual);


    }
}
