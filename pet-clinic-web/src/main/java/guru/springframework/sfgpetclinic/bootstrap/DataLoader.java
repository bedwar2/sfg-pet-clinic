package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.models.Owner;
import guru.springframework.sfgpetclinic.models.Pet;
import guru.springframework.sfgpetclinic.models.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.OwnerMapService;
import guru.springframework.sfgpetclinic.services.map.VetMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private  final OwnerService ownerService;
    private final VetService vetService;

    @Autowired
    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        //owner1.setId((long) 1);
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");


        Pet mikesPet = new Pet();
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setPetName("Roscoe");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        //owner2.setId(Long.valueOf(2));
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");

        Pet fionasCat = new Pet();
        fionasCat.setPetName("Just Cat");
        fionasCat.setOwner(owner2);
        fionasCat.setBirthDate(LocalDate.now());
        ownerService.save(owner2);

        System.out.println("Loaded owners");

        Vet vet1 = new Vet();
        //vet1.setId(Long.valueOf(1));
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");


        vetService.save(vet1);

        Vet vet2 = new Vet();
        //vet2.setId(Long.valueOf(2));
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet2);
        System.out.println("Loaded vets");
    }
}
