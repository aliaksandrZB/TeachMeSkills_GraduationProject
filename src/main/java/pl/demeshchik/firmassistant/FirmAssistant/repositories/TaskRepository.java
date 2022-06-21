package pl.demeshchik.firmassistant.FirmAssistant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.demeshchik.firmassistant.FirmAssistant.models.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findAllByFacilityId(int id);

    List<Task> findAllByPremiseId(int id);

    void deleteByFacilityIdAndId(int facilityId, int taskId);

    void deleteByPremiseIdAndId(int premiseId, int taskId);
}
