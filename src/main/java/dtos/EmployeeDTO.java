package dtos;


import entities.Employee;
import entities.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmployeeDTO {
    private Long id;
    private String name;
    private String address;
    private Long salary;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public EmployeeDTO(String name, String address, long salary) {
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public static List<EmployeeDTO> getEmployeeDtos(List<Employee> employees) {
        List<EmployeeDTO> employeedtos = new ArrayList();
        employees.forEach(employee -> employeedtos.add(new EmployeeDTO(employee)));
        return employeedtos;
    }

    public EmployeeDTO(Employee e) {
        if (e.getId() != null)
            this.id = e.getId();
            this.name = e.getName();
            this.address = e.getAddress();
            this.salary = e.getSalary();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeDTO)) return false;
        EmployeeDTO that = (EmployeeDTO) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
