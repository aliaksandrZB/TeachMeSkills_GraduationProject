package pl.demeshchik.firmassistant.FirmAssistant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.demeshchik.firmassistant.FirmAssistant.models.Premise;
import pl.demeshchik.firmassistant.FirmAssistant.models.Task;
import pl.demeshchik.firmassistant.FirmAssistant.servises.PremiseService;
import pl.demeshchik.firmassistant.FirmAssistant.servises.TaskService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TaskController {

    private final TaskService taskService;
    private final PremiseService premiseService;

    @Autowired
    public TaskController(TaskService taskService, PremiseService premiseService) {
        this.taskService = taskService;
        this.premiseService = premiseService;
    }

    @PostMapping("/facilities/{id}/tasks")
    public String create(@PathVariable("id") int facilityId, @ModelAttribute("newTask")Task task) {
        taskService.saveTaskOfFacility(facilityId, task);

        return "redirect:/facilities/{id}";
    }

    @PostMapping("/facilities/{id}/premises/{name}/tasks")
    public String create(@PathVariable("id") int facilityId, @PathVariable("name") String name,
                         @ModelAttribute("newTask")Task task) {
        Premise premise = premiseService.loadPremiseOfFacilityByName(facilityId, name).get();
        taskService.saveTaskOfPremise(premise.getId(), task);

        return "redirect:/facilities/{id}/premises/{name}";
    }

    @PatchMapping("/facilities/{id}/tasks")
    public String edit(@PathVariable("id") int facilityId,
                       @RequestParam(value = "checkboxName") int[] checkboxValues) {
        taskService.updateAllTaskOfFacility(facilityId, createMapWithCheckboxValues(checkboxValues));

        return "redirect:/facilities/{id}";
    }

    @PatchMapping("/facilities/{id}/premises/{name}/tasks")
    public String edit(@PathVariable("id") int facilityId, @PathVariable("name") String name,
                       @RequestParam(value = "checkboxName") int[] checkboxValues) {
        Premise premise = premiseService.loadPremiseOfFacilityByName(facilityId, name).get();
        taskService.updateAllTaskOfPremise(premise.getId(), createMapWithCheckboxValues(checkboxValues));

        return "redirect:/facilities/{id}/premises/{name}";
    }

    @GetMapping("/facilities/{id}/tasks/{task_id}")
    public String delete(@PathVariable("id") int facilityId, @PathVariable("task_id") int taskId) {
        taskService.deleteTaskOfFacilityById(facilityId, taskId);
        return "redirect:/facilities/{id}";
    }

    @GetMapping("/facilities/{id}/premises/{name}/tasks/{task_id}")
    public String delete(@PathVariable("id") int facilityId, @PathVariable("name") String name,
                         @PathVariable("task_id") int taskId) {
        Premise premise = premiseService.loadPremiseOfFacilityByName(facilityId, name).get();
        taskService.deleteTaskOfPremiseById(premise.getId(), taskId);
        return "redirect:/facilities/{id}/premises/{name}";
    }

    private Map<Integer, Boolean> createMapWithCheckboxValues(int[] checkboxValues) {
        Map<Integer, Boolean> map = new HashMap<>();

        for(int value : checkboxValues) {
            if (!map.containsKey(value)) {
                map.put(value, false);
            } else {
                map.put(value, true);
            }
        }

        return map;
    }

}
