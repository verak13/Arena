package arena.arena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import arena.arena.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	
	Person findByEmail(String email);
}