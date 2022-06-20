package pl.demeshchik.firmassistant.FirmAssistant.servises;

import com.sun.prism.impl.BaseMesh;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.demeshchik.firmassistant.FirmAssistant.models.Facility;
import pl.demeshchik.firmassistant.FirmAssistant.repositories.FacilityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FacilityService {

    private final FacilityRepository facilityRepository;

    @Autowired
    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public void save(Facility facility) {
        facilityRepository.save(facility);
    }
    public void update(Facility facility) {
        facilityRepository.save(facility);
    }

    public Optional<Facility> loadFacilityByAddress(String country, String city, String postcode,
                                                    String street, String houseNumber) {
        Optional<Facility> facility = facilityRepository.findByCountryAndCityAndPostcodeAndStreetAndHouseNumber(
                country, city, postcode,street, houseNumber);

        return facility;
    }

    public List<Facility> getAll() {
        return facilityRepository.findAll();
    }

    public Optional<Facility> getById(int id) {
        return facilityRepository.findById(id);
    }

    public void delete(int id) {
        facilityRepository.deleteById(id);
    }

    public List<Facility> getAllFacilitiesOfClients(int clientId) {
        return facilityRepository.findAllFacilityByClientsId(clientId);
    }
}
