package pl.demeshchik.firmassistant.FirmAssistant.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.demeshchik.firmassistant.FirmAssistant.models.Facility;
import pl.demeshchik.firmassistant.FirmAssistant.models.Premise;
import pl.demeshchik.firmassistant.FirmAssistant.models.Task;
import pl.demeshchik.firmassistant.FirmAssistant.repositories.TaskRepository;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> loadAllTasksOfFacility(int id) {
        return taskRepository.findAllByFacilityId(id);
    }

    public List<Task> loadAllTasksOfPremise(int id) {
        return taskRepository.findAllByPremiseId(id);
    }

    public void updateAllTaskOfFacility(int facilityId, Map<Integer, Boolean> map) {
        List<Task> tasks = taskRepository.findAllByFacilityId(facilityId);

        for (Task task : tasks) {
            int id = task.getId();
            task.setDone(map.get(id));
        }

        taskRepository.saveAll(tasks);
    }

    public void updateAllTaskOfPremise(int premiseId, Map<Integer, Boolean> map) {
        List<Task> tasks = taskRepository.findAllByPremiseId(premiseId);

        for (Task task : tasks) {
            int id = task.getId();
            task.setDone(map.get(id));
        }

        taskRepository.saveAll(tasks);
    }

    public void saveTaskOfFacility(int facilityId, Task task) {
        task.setFacility(new Facility());
        task.getFacility().setId(facilityId);

        taskRepository.save(task);
    }

    public void saveTaskOfPremise(int premiseId, Task task) {
        task.setPremise(new Premise());
        task.getPremise().setId(premiseId);

        taskRepository.save(task);
    }

    public void deleteTaskOfFacilityById(int facilityId, int taskId) {
        taskRepository.deleteByFacilityIdAndId(facilityId, taskId);
    }

    public void deleteTaskOfPremiseById(int premiseId, int taskId) {
        taskRepository.deleteByPremiseIdAndId(premiseId, taskId);
    }
}
