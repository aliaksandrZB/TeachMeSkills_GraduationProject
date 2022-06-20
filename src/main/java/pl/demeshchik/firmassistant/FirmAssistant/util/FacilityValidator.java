package pl.demeshchik.firmassistant.FirmAssistant.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.demeshchik.firmassistant.FirmAssistant.models.Facility;
import pl.demeshchik.firmassistant.FirmAssistant.servises.FacilityService;

@Component
public class FacilityValidator implements Validator {

    private final FacilityService facilityService;

    @Autowired
    public FacilityValidator(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Facility.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Facility facility = (Facility)target;

        if (facilityService.loadFacilityByAddress(facility.getCountry(), facility.getCity(), facility.getPostcode(),
                facility.getStreet(), facility.getHouseNumber()).isEmpty()) {
            return;
        }

        errors.rejectValue("houseNumber", "", "Строительный объект уже добавлен");
    }
}
