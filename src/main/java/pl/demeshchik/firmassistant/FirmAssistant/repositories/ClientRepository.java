package pl.demeshchik.firmassistant.FirmAssistant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.demeshchik.firmassistant.FirmAssistant.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    List<Client> findAllByFacilitiesId(int id);

    Optional<Client> findByPhoneNumber(String phoneNumber);

    void deleteClientByFacilitiesIdAndId(int facilityId, int id);

}
