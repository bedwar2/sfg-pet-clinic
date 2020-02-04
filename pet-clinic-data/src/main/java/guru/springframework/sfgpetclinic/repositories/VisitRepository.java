package guru.springframework.sfgpetclinic.repositories;

import guru.springframework.sfgpetclinic.models.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {

}
