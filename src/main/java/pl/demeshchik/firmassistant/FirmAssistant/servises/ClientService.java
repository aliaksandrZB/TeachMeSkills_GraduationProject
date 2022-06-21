package pl.demeshchik.firmassistant.FirmAssistant.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.demeshchik.firmassistant.FirmAssistant.models.Client;
import pl.demeshchik.firmassistant.FirmAssistant.models.Facility;
import pl.demeshchik.firmassistant.FirmAssistant.repositories.ClientRepository;
import pl.demeshchik.firmassistant.FirmAssistant.repositories.FacilityRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;
    private final FacilityRepository facilityRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, FacilityRepository facilityRepository) {
        this.clientRepository = clientRepository;
        this.facilityRepository = facilityRepository;
    }

    public void save(Client client) {
        clientRepository.save(client);
    }

    public List<Client> loadAllClientsOfFacility(int id) {
        return clientRepository.findAllByFacilitiesId(id);
    }

    public Client loadById(int id) {
        return clientRepository.findById(id).orElse(new Client());
    }

    public void saveForFacility(int facilityId, Client client) {
        Facility facility = facilityRepository.findById(facilityId).get();

        facility.setClients(new ArrayList<>(Collections.singletonList(client)));
        client.setFacilities(new ArrayList<>(Collections.singletonList(facility)));

        facilityRepository.save(facility);
        clientRepository.save(client);
    }

    public Optional<Client> loadClientByPhoneNumber(String phoneNumber) {
        return clientRepository.findByPhoneNumber(phoneNumber);
    }

    public void update(int facilityId, Client client) {
        Facility facility = facilityRepository.findById(facilityId).get();

        facility.setClients(new ArrayList<>(Collections.singletonList(client)));
        client.setFacilities(new ArrayList<>(Collections.singletonList(facility)));

        facilityRepository.save(facility);
        clientRepository.save(client);
    }

    @Transactional
    public void deleteClientFromFacility(int facilityId, int clientId) {
//        Facility facility = facilityRepository.findById(facilityId).get();
//        Client client = clientRepository.findById(clientId).get();
//
//        facility.getClients().remove(client);
//        client.getFacilities().remove(facility);


        clientRepository.deleteClientByFacilitiesIdAndId(facilityId, clientId);
    }
}
