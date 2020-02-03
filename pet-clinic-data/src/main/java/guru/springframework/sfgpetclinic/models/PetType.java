package guru.springframework.sfgpetclinic.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "types")
public class PetType extends BaseEntity {

    @Builder
    public PetType(Long id, String name) {
        super(id);
        this.name = name;
    }

    public PetType() {
        this.builder().build();
    }

    @Column(name = "name")
    private String name;

}
