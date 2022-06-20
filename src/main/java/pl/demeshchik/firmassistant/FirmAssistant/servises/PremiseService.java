package pl.demeshchik.firmassistant.FirmAssistant.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.demeshchik.firmassistant.FirmAssistant.models.Facility;
import pl.demeshchik.firmassistant.FirmAssistant.models.Premise;
import pl.demeshchik.firmassistant.FirmAssistant.repositories.FacilityRepository;
import pl.demeshchik.firmassistant.FirmAssistant.repositories.PremiseRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PremiseService {

    private final PremiseRepository premiseRepository;

    @Autowired
    public PremiseService(PremiseRepository premiseRepository) {
        this.premiseRepository = premiseRepository;
    }

    public void save(Premise premise) {
        premiseRepository.save(premise);
    }

    public List<Premise> loadAllPremisesOfFacility(int facilityId) {
        return premiseRepository.findAllByFacilityId(facilityId);
    }

    public Optional<Premise> loadPremiseOfFacilityByName(int id, String name) {
        return premiseRepository.findByFacilityIdAndName(id, name);
    }

    @Transactional
    public void update(int id, Premise premise) {
        premiseRepository.update(premise.getName(), id);
    }

    public void deletePremisesOfFacilityByName(int id, String name) {
        premiseRepository.deleteByFacilityIdAndName(id, name);
    }

}
