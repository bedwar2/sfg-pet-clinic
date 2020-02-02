package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.models.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String ListOwners(@ModelAttribute Owner owner, BindingResult result, Model model) {
        List<Owner> owners;
        if (owner != null && owner.getLastName() != null) {
            owners  = ownerService.findAllByLastNameLike(owner.getLastName());
            if (owners.isEmpty()) {
                result.rejectValue("lastName", "notFound", "not found");
                return "owners/findOwners";
            } else if (owners.size() == 1) {
                owner = owners.iterator().next();
                return "redirect:/owners/" + owner.getId();
            }
        } else {
            owners = StreamSupport.stream(ownerService.findAll().spliterator(), false).collect(Collectors.toList());
        }

        model.addAttribute("owners", owners);

        return "/owners/index";
    }

    @RequestMapping("/find")
    public String ownerFind(Model model)
    {
        model.addAttribute("owner", new Owner());

        return "owners/findOwners";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView getOwnerById(@PathVariable String ownerId) {
        Owner owner = ownerService.findById(Long.valueOf(ownerId));

        ModelAndView mv = new ModelAndView("owners/ownerDetails");
        mv.addObject(owner);

        return mv;
    }



}
