package telran.java52.person.service;

import java.time.LocalDate;
import java.util.List;

import telran.java52.person.dto.AddressDto;
import telran.java52.person.dto.PersonDto;


public interface PersonService {
    Boolean addPerson(PersonDto personDto);

    PersonDto findPersonById(Integer id);

    List<PersonDto> findPersonByCity(String city);

    List<PersonDto> findPersonByAge(LocalDate minAge, LocalDate maxAge);

    PersonDto updateName(Integer id, String name);

    List<PersonDto> findPersonByName(String name);

    PersonDto updateAddress(Integer id, AddressDto addressDto);

    PersonDto removePerson(Integer id);

}
