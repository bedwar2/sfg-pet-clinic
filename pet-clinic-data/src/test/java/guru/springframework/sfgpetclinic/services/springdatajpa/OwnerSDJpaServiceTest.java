package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.models.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {


    @Mock
    private  OwnerRepository ownerRepository;

    @Mock
    private  PetRepository petRepository;

    @Mock
    private  PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;

    Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(1L).lastName(SMITH).build();
        //service = new OwnerSDJpaService(ownerRepository,petRepository,petTypeRepository);

    }

    final String SMITH = "Smith";

    @Test
    void findByLastName() {

        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

        Owner smith = service.findByLastName(SMITH);
        assertEquals(1L , smith.getId());
        assertEquals(SMITH , smith.getLastName());

    }

    @Test
    void findAll() {
        Set<Owner> returnedOwnersSet = new HashSet<>();
        returnedOwnersSet.add(Owner.builder().id(1L).firstName("Jeff").build());
        returnedOwnersSet.add(Owner.builder().id(2L).firstName("Peyton").build());

        when(ownerRepository.findAll()).thenReturn(returnedOwnersSet);

        Set<Owner> owners = service.findAll();
        assertNotNull(owners);
        assertEquals(2,owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        Owner owner = service.findById(1L);

        assertNotNull(owner);
    }

    @Test
    void save() {

        Owner ownerToSave = Owner.builder().id(1L).build();

        when(ownerRepository.save(any())).thenReturn(returnOwner);

        Owner savedOwner = service.save(ownerToSave);

        assertNotNull(savedOwner);

        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnOwner);

        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(returnOwner.getId());

        verify(ownerRepository).deleteById(any());
    }
}