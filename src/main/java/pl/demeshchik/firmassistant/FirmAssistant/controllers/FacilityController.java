package pl.demeshchik.firmassistant.FirmAssistant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.demeshchik.firmassistant.FirmAssistant.models.Facility;
import pl.demeshchik.firmassistant.FirmAssistant.models.Task;
import pl.demeshchik.firmassistant.FirmAssistant.servises.ClientService;
import pl.demeshchik.firmassistant.FirmAssistant.servises.FacilityService;
import pl.demeshchik.firmassistant.FirmAssistant.servises.PremiseService;
import pl.demeshchik.firmassistant.FirmAssistant.servises.TaskService;
import pl.demeshchik.firmassistant.FirmAssistant.util.FacilityValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/facilities")
public class FacilityController {

    private final FacilityService facilityService;
    private final PremiseService premiseService;
    private final TaskService taskService;
    private final FacilityValidator facilityValidator;

    @Autowired
    public FacilityController(FacilityService facilityService, PremiseService premiseService, ClientService clientService, TaskService taskService, FacilityValidator facilityValidator) {
        this.facilityService = facilityService;
        this.premiseService = premiseService;
        this.taskService = taskService;
        this.facilityValidator = facilityValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("facilities", facilityService.getAll());
        return "facilities/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Task task = new Task();
        model.addAttribute("facility", facilityService.getById(id).get());
        model.addAttribute("premises", premiseService.loadAllPremisesOfFacility(id));
        model.addAttribute("tasks", taskService.loadAllTasksOfFacility(id));
        model.addAttribute("newTask", task);
        return "facilities/show";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("facility") Facility facility) {
        return "facilities/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("facility") @Valid Facility facility,
                         BindingResult bindingResult) {

        facilityValidator.validate(facility, bindingResult);

        if (bindingResult.hasErrors()) {
            return "facilities/new";
        }

        facilityService.save(facility);
        return "redirect:/facilities";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("facility", facilityService.getById(id).get());
        return "facilities/edit";
    }

    @PatchMapping("/{id}")
    public String edit(@ModelAttribute("facility") @Valid Facility facility, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "facilities/edit";
        }

        facilityService.update(facility);
        return "redirect:/facilities/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        facilityService.delete(id);
        return "redirect:/facilities";
    }

}
