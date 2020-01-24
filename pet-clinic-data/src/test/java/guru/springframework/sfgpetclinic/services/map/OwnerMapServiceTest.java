package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.models.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    final Long ownerId = 1L;
    final String lastName = "Edwards";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());


        Owner owner = Owner.builder().id(ownerId).lastName(lastName).firstName("Brian").city("Ellicott City").build();
        ownerMapService.save(owner);
    }

    @Test
    void findAll() {
         Set<Owner> owners = ownerMapService.findAll();

         assertEquals(1, owners.size());
    }

    @Test
    void findById() {

        assertEquals(ownerId, ownerMapService.findById(ownerId).getId());
    }

    @Test
    void save() {
        Owner owner = Owner.builder().id(2L).firstName("Brian").lastName("Edwards").build();

        Owner savedOwner = ownerMapService.save(owner);

        assertEquals("Brian", savedOwner.getFirstName());
    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerMapService.save(Owner.builder().lastName("Jones").build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void delete() {

        ownerMapService.delete(ownerMapService.findById(ownerId));

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        assertEquals(ownerId,ownerMapService.findByLastName(lastName).getId());

        assertNull(ownerMapService.findByLastName("Not Exists"));
    }
}