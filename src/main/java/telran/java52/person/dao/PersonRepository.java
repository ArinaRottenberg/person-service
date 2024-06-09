package telran.java52.person.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import telran.java52.person.dto.CityPopulationDto;
import telran.java52.person.dto.PersonDto;
import telran.java52.person.model.Child;
import telran.java52.person.model.Employee;
import telran.java52.person.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

	Stream<Person> findByNameIgnoreCase(String name);
	
    Stream<Person> findByAddressCityIgnoreCase(@Param("cityName")String city);
	
	Stream<Person> findByBirthDateBetween(LocalDate from, LocalDate to);

	@Query ("select new telran.java52.person.dto.CityPopulationDto(p.address.city, count(p)) from Person p group by p.address.city order by count(p)")
	
	List<CityPopulationDto> getCitiesPopulation();
	
	
	@Query("SELECT p FROM Employee p WHERE p.salary BETWEEN :minSalary AND :maxSalary")
	Stream<Employee> findEmployeesBySalary(@Param("minSalary") Integer minSalary, @Param("maxSalary") Integer maxSalary);

}
