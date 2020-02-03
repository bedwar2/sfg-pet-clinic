package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.models.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/owners")
public class OwnerController {
    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
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

    @GetMapping("/new")
    public ModelAndView initOwnerForm() {
        ModelAndView mv = new ModelAndView(VIEWS_OWNER_CREATE_OR_UPDATE_FORM);

        mv.addObject(Owner.builder().build());

        return mv;
    }

    @PostMapping("/new")
    public String processCreateForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public ModelAndView initUpdateOwnerForm(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mv = new ModelAndView(VIEWS_OWNER_CREATE_OR_UPDATE_FORM);
        mv.addObject(ownerService.findById(ownerId));
        return mv;
    }

    @PostMapping("/{ownerId}/edit")
    public String processOwnerUpdateForm(@Valid Owner owner, BindingResult result, @PathVariable("ownerId") Long ownerId) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            owner.setId(ownerId);
            ownerService.save(owner);
            return "redirect:/owners/{ownerId}";
        }
    }


}
