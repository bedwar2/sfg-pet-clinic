package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.models.Owner;
import guru.springframework.sfgpetclinic.models.Pet;

import java.util.Set;

public interface PerService {
    Pet  findById(Long id);
    Pet save(Pet pet);
    Set<Pet> findAll();
}
