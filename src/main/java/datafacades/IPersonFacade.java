package datafacades;

import dtos.PersonDTO;

public interface IPersonFacade {

    public PersonDTO addPerson(String fName, String lName, String phone);
    public PersonDTO deletePerson(int id) throws Exception;
    public PersonDTO getPersonById(int id) throws Exception;
    public PersonDTO getAllPersons();
    public PersonDTO updatePerson(PersonDTO p) throws Exception;

}
