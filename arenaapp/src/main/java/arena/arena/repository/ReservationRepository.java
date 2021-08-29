package arena.arena.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import arena.arena.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	@Query("SELECT r FROM Reservation r WHERE r.email = ?1")
	Page<Reservation> getByEmail(String email, Pageable pageable);

	@Query("SELECT COUNT(r.id) FROM Reservation r WHERE r.email = ?1 AND r.event.id = ?2 AND r.active = true")
	Long checkReserved(String email, Long eventId);

	@Query("SELECT r FROM Reservation r WHERE r.event.id = ?1 AND r.seatName = ?2 AND r.email = ?3")
	Reservation findByEventAndSeatAndEmail(Long eventId, String seatName, String email);
	
	@Query("SELECT r.seatName FROM Reservation r WHERE r.event.id = ?1 AND r.email = ?2 AND r.active = true")
	List<String> getMySeats(Long eventId, String email);
	
	@Query("SELECT r FROM Reservation r WHERE r.event.id = ?1")
	Page<Reservation> getByEvent(Long eventId, Pageable pageable);
}
