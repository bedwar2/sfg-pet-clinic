package guru.springframework.sfgpetclinic.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"owner"}, callSuper = false)
@ToString(exclude = {"owner"})
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {

    public Pet() {
        super();
    }

    @Builder
    public Pet(Long id, String petName, PetType petType, Owner owner, LocalDate birthDate, Set<Visit> visits) {
        super(id);
        this.petName = petName;
        this.petType = petType;
        this.owner = owner;
        this.birthDate = birthDate;
        this.visits = visits;
    }

    @Column(name = "pet_name")
    private String petName;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet", fetch = FetchType.EAGER)
    @OrderBy("date descending")
    private Set<Visit> visits = new HashSet<>();

}
