package pl.demeshchik.firmassistant.FirmAssistant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.demeshchik.firmassistant.FirmAssistant.models.Facility;
import pl.demeshchik.firmassistant.FirmAssistant.models.Premise;
import pl.demeshchik.firmassistant.FirmAssistant.models.Task;
import pl.demeshchik.firmassistant.FirmAssistant.servises.PremiseService;
import pl.demeshchik.firmassistant.FirmAssistant.servises.TaskService;
import pl.demeshchik.firmassistant.FirmAssistant.util.PremiseValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("facilities/{id}/premises")
public class PremiseController {

    public final PremiseService premiseService;
    public final TaskService taskService;
    public final PremiseValidator premiseValidator;

    @Autowired
    public PremiseController(PremiseService premiseService, TaskService taskService, PremiseValidator premiseValidator) {
        this.premiseService = premiseService;
        this.taskService = taskService;
        this.premiseValidator = premiseValidator;
    }

    @GetMapping("/{name}")
    public String show(@PathVariable("id") int id, @PathVariable("name") String name, Model model) {
        Task task = new Task();
        Premise premise = premiseService.loadPremiseOfFacilityByName(id, name).get();
        model.addAttribute("premise", premise);
        model.addAttribute("facilityId", id);
        model.addAttribute("tasks", taskService.loadAllTasksOfPremise(premise.getId()));
        model.addAttribute("newTask", task);
        return "premises/show";
    }

    @GetMapping("/new")
    public String create(@PathVariable("id") int id, @ModelAttribute("premise") Premise premise, Model model) {
        model.addAttribute("facilityId", id);
        return "premises/new";
    }

    @PostMapping()
    public String create(@PathVariable("id") int id, @ModelAttribute("premise") @Valid Premise premise,
                         BindingResult bindingResult) {
        premise.setFacility(new Facility());
        premise.getFacility().setId(id);
        premiseValidator.validate(premise, bindingResult);

        if (bindingResult.hasErrors()) {
            return "premises/new";
        }

        premiseService.save(premise);
        return "redirect:/facilities/{id}";
    }

    @GetMapping("/{name}/edit")
    public String edit(@PathVariable("id") int id, @PathVariable("name") String name, Model model) {
        model.addAttribute("premise", premiseService.loadPremiseOfFacilityByName(id, name).get());
        model.addAttribute("facilityId", id);
        return "premises/edit";
    }

    @PatchMapping("/{name}")
    public String edit(@PathVariable("id") int facilityId, @PathVariable("name") String name,
                       @ModelAttribute("premise") @Valid Premise premise, BindingResult bindingResult,
                       RedirectAttributes attr) {
        if (bindingResult.hasErrors()) {
            return "premises/edit";
        }

        int id = premiseService.loadPremiseOfFacilityByName(facilityId, name).get().getId();
        attr.addAttribute("name", premise.getName());
        premiseService.update(id, premise);
        return "redirect:/facilities/{id}/premises/{name}";
    }

    @DeleteMapping("/{name}")
    public String delete(@PathVariable("id") int id, @PathVariable("name") String name) {
        premiseService.deletePremisesOfFacilityByName(id, name);
        return "redirect:/facilities/{id}";
    }

}
