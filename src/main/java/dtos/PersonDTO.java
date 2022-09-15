/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Address;
import entities.Person;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author tha
 */
public class PersonDTO {
    private int id;
    private String fname;
    private String lname;
    private String phone;
    private Date created;   // LocalDate virker
    private Date lastEdited;
    private Address address;

    public PersonDTO(Person p) {
        if (p.getId() != 0)
            this.id = p.getId();
        this.fname = p.getFname();
        this.lname = p.getLname();
        this.phone = p.getPhone();
        this.address = p.getAddress();
    }

    public static List<PersonDTO> toList(List<Person> persons) {
        return persons.stream().map(PersonDTO::new).collect(Collectors.toList());
    }

    public Person getEntity(){
        Person p = new Person(this.fname, this.lname, this.phone);
        if(id != 0)
            p.setId(this.id);
        return p;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(Date lastEdited) {
        this.lastEdited = lastEdited;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", phone='" + phone + '\'' +
                ", created=" + created +
                ", lastEdited=" + lastEdited +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonDTO)) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return getId() == personDTO.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
