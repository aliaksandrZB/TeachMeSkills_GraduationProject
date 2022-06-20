package pl.demeshchik.firmassistant.FirmAssistant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.demeshchik.firmassistant.FirmAssistant.models.Premise;

import java.util.List;
import java.util.Optional;

public interface PremiseRepository extends JpaRepository<Premise, Integer> {

    Optional<Premise> findByName(String name);

    Optional<Premise> findByFacilityIdAndName(int id, String name);

    List<Premise> findAllByFacilityId(int facilityId);

    void deleteByFacilityIdAndName(int id, String name);

    @Modifying
    @Query(value = "update Premise u set u.name = :name where u.id = :id")
    void update(@Param("name") String name, @Param("id") int id);
}
