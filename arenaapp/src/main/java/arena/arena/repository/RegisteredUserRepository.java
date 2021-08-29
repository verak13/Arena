package arena.arena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import arena.arena.model.RegisteredUser;

@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {

	RegisteredUser findByEmail(String email);
}
