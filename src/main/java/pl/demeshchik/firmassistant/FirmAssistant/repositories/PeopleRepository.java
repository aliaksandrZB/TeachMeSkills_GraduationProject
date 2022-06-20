package pl.demeshchik.firmassistant.FirmAssistant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.demeshchik.firmassistant.FirmAssistant.models.Person;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer>{

    Optional<Person> findByUsername(String username);

}
