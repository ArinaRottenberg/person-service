package telran.java52.person.dao;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telran.java52.person.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findByAddressCity(String city);

    List<Person> findPersonByBirthDateBetween(LocalDate from, LocalDate to);
    
    List<Person> findByName(String name);

}
