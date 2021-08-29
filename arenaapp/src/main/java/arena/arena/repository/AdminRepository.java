package arena.arena.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import arena.arena.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	 Admin findByEmail(String email);

}
