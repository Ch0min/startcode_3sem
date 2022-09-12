package facades;

import dtos.EmployeeDTO;
import dtos.PersonDTO;
import entities.Employee;
import entities.Person;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;

    private EmployeeFacade() {
    }

    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // 1
    public EmployeeDTO getEmployeeById(long id) {
        EntityManager em = emf.createEntityManager();
        Employee employee = em.find(Employee.class, id);
        return new EmployeeDTO(employee);
    }

    // 2
    public List<EmployeeDTO> getEmployeeByName(String name) {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<EmployeeDTO> query = em.createQuery("SELECT e FROM Employee e WHERE e.name = :name", EmployeeDTO.class);
            query.setParameter("name", name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }


    // 3
    public List<EmployeeDTO> getAllEmployees() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        List<Employee> employees = query.getResultList();
        return EmployeeDTO.getEmployeeDtos(employees);
    }

    // 4
    public List<Employee> getEmployeesWithHighestSalaryFromTop() {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e ORDER BY e.salary DESC", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // 5
    public EmployeeDTO create(EmployeeDTO employeeDTO) {
        Employee employeeEntity = new Employee(employeeDTO.getName(), employeeDTO.getAddress(), employeeDTO.getSalary());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employeeEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new EmployeeDTO(employeeEntity);
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        EmployeeFacade ef = getEmployeeFacade(emf);
        System.out.println(ef.getEmployeesWithHighestSalaryFromTop());

//        ef.getAllEmployees().forEach(dto->System.out.println(dto));
    }

}
