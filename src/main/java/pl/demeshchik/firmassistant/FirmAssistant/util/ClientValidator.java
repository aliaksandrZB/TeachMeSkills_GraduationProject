package pl.demeshchik.firmassistant.FirmAssistant.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.demeshchik.firmassistant.FirmAssistant.models.Client;
import pl.demeshchik.firmassistant.FirmAssistant.servises.ClientService;

@Component
public class ClientValidator implements Validator {

    private final ClientService clientService;

    @Autowired
    public ClientValidator(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Client.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Client client = (Client)target;

        if (clientService.loadClientByPhoneNumber(client.getPhoneNumber()).isEmpty()) {
            return;
        }

        errors.rejectValue("email", "", "Клиент уже добавлен");
    }
}
