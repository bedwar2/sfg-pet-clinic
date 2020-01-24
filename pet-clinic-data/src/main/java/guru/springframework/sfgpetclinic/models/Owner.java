package guru.springframework.sfgpetclinic.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "owners")
public class Owner extends Person {

    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;

    @Column(name = "telephone")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    public Owner() {
    }

    @Builder
    public Owner(Long id, String firstName, String lastName, String address, String city,
                 String telephone, Set<Pet> pets) {
        super.setId(id);
        super.setFirstName(firstName);
        super.setLastName(lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;

        if(pets != null) {
            this.pets = pets;
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Owner;
    }

}

