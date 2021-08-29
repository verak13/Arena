package arena.arena.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import arena.arena.model.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

	@Query("SELECT s FROM Event e JOIN e.seats s WHERE e.id = ?1 ORDER BY s.id")
	List<Seat> getByEvent(Long eventId);

}
