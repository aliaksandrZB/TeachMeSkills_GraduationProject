package pl.demeshchik.firmassistant.FirmAssistant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.demeshchik.firmassistant.FirmAssistant.models.Facility;
import pl.demeshchik.firmassistant.FirmAssistant.models.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Integer> {

    Optional<Facility> findByCountryAndCityAndPostcodeAndStreetAndHouseNumber(String country, String city,
                                                                              String postcode, String street,
                                                                              String houseNumber);

    List<Facility> findAllFacilityByClientsId(int id);
}
