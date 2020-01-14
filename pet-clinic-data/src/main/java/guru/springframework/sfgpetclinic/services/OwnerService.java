package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.models.Owner;

import java.util.Set;

public interface OwnerService {
    Owner findById(Long id);
    Owner save(Owner owner);
    Owner findByLastName(String lastName);
    Set<Owner> findAll();
}
