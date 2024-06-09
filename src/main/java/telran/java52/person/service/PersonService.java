package telran.java52.person.service;

import java.util.List;

import telran.java52.person.dto.AddressDto;
import telran.java52.person.dto.ChildDto;
import telran.java52.person.dto.CityPopulationDto;
import telran.java52.person.dto.EmployeeDto;
import telran.java52.person.dto.PersonDto;


public interface PersonService {
    Boolean addPerson(PersonDto personDto);

    PersonDto findPersonById(Integer id);

    PersonDto[] findPersonsByCity(String city);

    PersonDto[] findPersonsBetweenAge(Integer minAge, Integer maxAge);

    PersonDto updatePersonName(Integer id, String name);

    PersonDto[] findPersonsByName(String name);

    PersonDto updatePersonAddress(Integer id, AddressDto addressDto);

    PersonDto removePerson(Integer id);
    
    Iterable<CityPopulationDto> getCitiesPopulation();
    
    ChildDto[] findAllChildren();

    EmployeeDto[] findEmployeesBySalary(Integer minSalary, Integer maxSalary);

}
