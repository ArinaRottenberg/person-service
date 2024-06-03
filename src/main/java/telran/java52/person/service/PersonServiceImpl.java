package telran.java52.person.service;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.java52.person.dao.PersonRepository;
import telran.java52.person.dto.AddressDto;
import telran.java52.person.dto.PersonDto;
import telran.java52.person.dto.exceptions.PersonNotFoundException;
import telran.java52.person.model.Address;
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
		List<Person> persons = personRepository.findByAddressCity(city);
		return persons.stream().map(person -> modelMapper.map(person, PersonDto.class)).toList();
	}

	@Override
	public List<PersonDto> findPersonByAge(int minAge, int maxAge) {
		LocalDate minDate = LocalDate.now().minusYears(maxAge);
		LocalDate maxDate = LocalDate.now().minusYears(minAge).plusDays(1);

		List<Person> persons = personRepository.findPersonByBirthDateBetween(minDate, maxDate);
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
	public PersonDto updateAddress(Integer id, AddressDto addressDto) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		Address address = new Address(addressDto.getCity(), addressDto.getStreet(), addressDto.getBuilding());
		person.setAddress(address);
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
