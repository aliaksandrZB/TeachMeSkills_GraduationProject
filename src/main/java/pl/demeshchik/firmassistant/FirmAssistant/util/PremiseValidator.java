package pl.demeshchik.firmassistant.FirmAssistant.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.demeshchik.firmassistant.FirmAssistant.models.Premise;
import pl.demeshchik.firmassistant.FirmAssistant.servises.PremiseService;

@Component
public class PremiseValidator implements Validator {

    private final PremiseService premiseService;

    @Autowired
    public PremiseValidator(PremiseService premiseService) {
        this.premiseService = premiseService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Premise.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Premise premise = (Premise) target;

        int id = premise.getFacility().getId();
        if (premiseService.loadPremiseOfFacilityByName(id, premise.getName()).isEmpty()) {
            return;
        }

        errors.rejectValue("name", "", "Комната уже существует");
    }
}
