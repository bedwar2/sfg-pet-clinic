package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.models.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private  final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    @Autowired
    public DataLoader(OwnerService ownerService,
                      VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        if (petTypeService.findAll().size() == 0) {
            loadData();
        }

    }

    private void loadData() {
        PetType petType = PetType.builder().build();
        petType.setName("Dog");
        //petType.setId(1L);
        petType = petTypeService.save(petType);

        PetType petType2 = PetType.builder().build();
        petType2.setName("Cat");
        //petType.setId(2L);
        petType2 = petTypeService.save(petType2);


        Owner owner1 = new Owner();
        //owner1.setId((long) 1);
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Test Street");
        owner1.setCity("Ellicott City");
        owner1.setTelephone("301-888-5555");


        Pet mikesPet = new Pet();
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setPetType(petType);
        mikesPet.setPetName("Roscoe");
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        //owner2.setId(Long.valueOf(2));
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setCity("Columbia");
        owner2.setAddress("3127 Kings Road");
        owner2.setTelephone("410-555-1212");

        Pet fionasCat = new Pet();
        fionasCat.setPetName("Just Cat");
        fionasCat.setPetType(petType2);
        fionasCat.setOwner(owner2);
        fionasCat.setBirthDate(LocalDate.now());
        owner2.getPets().add(fionasCat);
        ownerService.save(owner2);

        Visit catVisit = new Visit();
        catVisit.setPet(fionasCat);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Sneezy Kitty");
        visitService.save(catVisit);

        System.out.println("Loaded owners");

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        radiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        surgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        dentistry = specialtyService.save(dentistry);

        Vet vet1 = new Vet();
        //vet1.setId(Long.valueOf(1));
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialties().add(radiology);
        vet1.getSpecialties().add(dentistry);


        vetService.save(vet1);

        Vet vet2 = new Vet();
        //vet2.setId(Long.valueOf(2));
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialties().add(surgery);
        vet2.getSpecialties().add(radiology);

        vetService.save(vet2);
        System.out.println("Loaded vets");


    }
}
