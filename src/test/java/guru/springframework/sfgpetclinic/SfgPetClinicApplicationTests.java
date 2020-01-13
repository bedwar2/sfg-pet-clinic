package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SfgPetClinicApplicationTests {
	//Robert "Uncle Bob" Martin
	//S = Single Responsibility - every class should have a single responsibility, class should be small, avoid god classes, split big classes into smaller classes
	//O = Open Closed Principle - open for extension, but closed for modification, extend for behavior but don't modify it, getters and setters only when you need them, use abstract base classes
	//L = Liskov Substitution Principle - Object replaceable by subtypes without altering the correctness of the program, violations will fail the "is a" test
	//I = Interface Segregation Principle - fine-grained interfaces that are client specific.  Many client interfaces are better than one general purpose interface, avoid god interfaces
	//D = Dependency Inversion Principle


	@Test
	void contextLoads() {
	}

}
