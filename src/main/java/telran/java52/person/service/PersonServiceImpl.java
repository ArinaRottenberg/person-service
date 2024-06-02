package telran.java52.person.service;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.java52.person.dao.PersonRepository;
import telran.java52.person.dto.AddresDto;
import telran.java52.person.dto.PersonDto;
import telran.java52.person.dto.exceptions.PersonNotFoundException;
import telran.java52.person.model.Addres;
import telran.java52.person.model.Person;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    final PersonRepository personRepository;
    final ModelMapper modelMapper;

    @Override
    @Transactional
    public Boolean addPerson(PersonDto personDto) {
        if (personRepository.existsById(personDto.getId())) {
            return false;
        }
        personRepository.save(modelMapper.map(personDto, Person.class));
        return true;
    }

    @Override
    public PersonDto findPersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public List<PersonDto> findPersonByCity(String city) {
        List<Person> persons = personRepository.findByAddresCity(city);
        return persons.stream().map(person -> modelMapper.map(person, PersonDto.class)).toList();
    }
    
    @Override
    public List<PersonDto> findPersonByAge(LocalDate dateFrom, LocalDate dateTo) {
        List<Person> persons = personRepository.findPersonByBirthDateBetween(dateFrom, dateTo);
        return persons.stream().map(person -> modelMapper.map(person, PersonDto.class)).toList();
    }

    @Override
    @Transactional
    public PersonDto updateName(Integer id, String name) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setName(name);
        personRepository.save(person);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public List<PersonDto> findPersonByName(String name) {
        List<Person> persons = personRepository.findByName(name);
        return persons.stream().map(person -> modelMapper.map(person, PersonDto.class)).toList();
    }

    @Override
    @Transactional
    public PersonDto updateAddres(Integer id, AddresDto addresDto) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        Addres address = new Addres(addresDto.getCity(), addresDto.getStreet(), addresDto.getBuilding());
        person.setAddres(address);
        personRepository.save(person);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    @Transactional
    public PersonDto removePerson(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        personRepository.delete(person);
        return modelMapper.map(person, PersonDto.class);
    }

}
