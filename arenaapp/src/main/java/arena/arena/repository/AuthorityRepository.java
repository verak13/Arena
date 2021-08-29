package arena.arena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import arena.arena.model.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
