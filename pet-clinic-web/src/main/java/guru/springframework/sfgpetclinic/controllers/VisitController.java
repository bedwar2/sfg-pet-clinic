package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.models.Pet;
import guru.springframework.sfgpetclinic.models.Visit;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
public class VisitController {
    private static final String VIEWS_VISITS_CREATE_OR_UPDATE_FORM  = "/pets/createOrUpdateVisitForm";
    private final PetService petService;
    private final OwnerService ownerService;
    private final VisitService visitService;


    public VisitController(PetService petService, OwnerService ownerService, VisitService visitService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.visitService = visitService;
    }

    @GetMapping("/new")
    public String addNewVisit(@PathVariable Long ownerId, @PathVariable Long petId, Model model) {
        Visit visit = new Visit();
        Pet pet = petService.findById(petId);
        pet.setOwner(ownerService.findById(ownerId));
        visit.setPet(pet);

        model.addAttribute("visit", visit);

        return VIEWS_VISITS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String SaveNewVisit(@PathVariable Long ownerId, @PathVariable Long petId,
                               @ModelAttribute Visit visit, BindingResult result, Model model) {
        Pet pet = petService.findById(petId);
        pet.setOwner(ownerService.findById(ownerId));
        visit.setPet(pet);

        if (result.hasErrors()) {
            model.addAttribute("visit", visit);
            return VIEWS_VISITS_CREATE_OR_UPDATE_FORM;
        } else {
            visitService.save(visit);
            return "redirect:/owners/" + ownerId ;
        }
    }

}
