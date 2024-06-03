package telran.java52.person.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java52.person.dto.AddressDto;
import telran.java52.person.dto.PersonDto;
import telran.java52.person.service.PersonService;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
	final PersonService personService;

	@PostMapping
	public Boolean addPerson(@RequestBody PersonDto personDto) {
		return personService.addPerson(personDto);
	}

	@GetMapping("/id")
	public PersonDto findPersonById(@PathVariable Integer id) {
		return personService.findPersonById(id);
	}
	
	@GetMapping("/city/{city}")
	public List<PersonDto> findPersonByCity(@PathVariable String city) {
		return personService.findPersonByCity(city);
	}
	
	@GetMapping("/ages/{minAge}/{maxAge}")
	public List<PersonDto> findPersonByAge(@PathVariable LocalDate minAge, @PathVariable LocalDate maxAge) {
		return personService.findPersonByAge(minAge, maxAge);
	}
	
	@PutMapping("/id/{id}/name/{name}")
	public PersonDto updateName(@PathVariable Integer id, @PathVariable String name) {
		return personService.updateName(id, name);
	}
	
	@GetMapping("/name/{name}")
	public List<PersonDto> findPersonByName(@PathVariable String name) {
		return personService.findPersonByName(name);
	}
	
	@PutMapping("/{id}/adress")
	public PersonDto updateAdress(@PathVariable Integer id, @RequestBody AddressDto addressDto) {
		return personService.updateAddress(id, addressDto);
	}
	
	@DeleteMapping("/{id}")
	public PersonDto removePerson(@PathVariable Integer id) {
		return personService.removePerson(id);
	}
	
	
	
	
}
