package pl.demeshchik.firmassistant.FirmAssistant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.demeshchik.firmassistant.FirmAssistant.models.Client;
import pl.demeshchik.firmassistant.FirmAssistant.models.Premise;
import pl.demeshchik.firmassistant.FirmAssistant.servises.ClientService;
import pl.demeshchik.firmassistant.FirmAssistant.servises.FacilityService;
import pl.demeshchik.firmassistant.FirmAssistant.util.ClientValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("facilities/{id}/clients")
public class ClientController {

    private final ClientService clientService;
    private final FacilityService facilityService;
    private final ClientValidator clientValidator;

    @Autowired
    public ClientController(ClientService clientService, FacilityService facilityService, ClientValidator clientValidator) {
        this.clientService = clientService;
        this.facilityService = facilityService;
        this.clientValidator = clientValidator;
    }

    @GetMapping()
    public String index(@PathVariable("id") int id, Model model) {
        model.addAttribute("clients", clientService.loadAllClientsOfFacility(id));
        model.addAttribute("facilityId", id);
        return "clients/index";
    }

    @GetMapping("/{client_id}")
    public String show(@PathVariable("id") int id, @PathVariable("client_id") int clientId,
                       Model model) {
        model.addAttribute("client", clientService.loadById(clientId));
        model.addAttribute("facilityId", id);
        model.addAttribute("facilities", facilityService.getAllFacilitiesOfClients(clientId));
        return "clients/show";
    }

    @GetMapping("/new")
    public String create(@PathVariable("id") int facilityId, Model model) {
        model.addAttribute("facilityId", facilityId);
        Client client = new Client();
        model.addAttribute("client", client);

        return "clients/new";
    }

    @PostMapping()
    public String createForFacility(@PathVariable("id") int facilityId, @ModelAttribute("client") @Valid Client client,
                                    BindingResult bindingResult) {
        client.setId(0);
        clientValidator.validate(client, bindingResult);

        if (bindingResult.hasErrors()) {
            return "clients/new";
        }
        clientService.saveForFacility(facilityId, client);
        return "redirect:/facilities/{id}";
    }

    @GetMapping("/{client_id}/edit")
    public String edit(@PathVariable("id") int id, @PathVariable("client_id") int clientId, Model model) {
        model.addAttribute("client", clientService.loadById(clientId));
        model.addAttribute("facilityId", id);
        model.addAttribute("clientId", clientId);
        return "clients/edit";
    }

    @PatchMapping("/{client_id}")
    public String edit(@PathVariable("id") int facilityId, @PathVariable("client_id") int id,
                       @ModelAttribute("client") @Valid Client client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "premises/edit";
        }

        client.setId(id);

        clientService.update(facilityId, client);
        return "redirect:/facilities/{id}/clients/{client_id}";
    }

    @DeleteMapping("/{client_id}")
    public String delete(@PathVariable("id") int facilityId, @PathVariable("client_id") int clientId) {
        clientService.deleteClientFromFacility(facilityId, clientId);
        return "redirect:/facilities/{id}/clients";
    }

}
