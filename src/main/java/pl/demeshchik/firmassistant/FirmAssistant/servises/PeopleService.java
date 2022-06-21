package pl.demeshchik.firmassistant.FirmAssistant.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.demeshchik.firmassistant.FirmAssistant.models.Person;
import pl.demeshchik.firmassistant.FirmAssistant.repositories.PeopleRepository;
import pl.demeshchik.firmassistant.FirmAssistant.security.PersonDetails;

import java.util.Optional;

@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Optional<Person> loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(username);

        return person;
    }

}
