package pl.demeshchik.firmassistant.FirmAssistant.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.demeshchik.firmassistant.FirmAssistant.models.Person;
import pl.demeshchik.firmassistant.FirmAssistant.servises.PeopleService;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person)target;

        if (peopleService.loadUserByUsername(person.getUsername()).isEmpty()) {
            return;
        }

        errors.rejectValue("username", "", "Человек с таким именем уже существует");
    }

}