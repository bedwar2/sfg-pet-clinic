package guru.springframework.sfgpetclinic.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(exclude = {"pet"})
@ToString(exclude = {"pet"})
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

    @Column(name = "local_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

}
