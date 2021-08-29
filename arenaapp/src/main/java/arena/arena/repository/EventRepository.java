package arena.arena.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import arena.arena.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	
	@Query("SELECT COUNT(e.id) FROM Event e WHERE ?1 BETWEEN e.startDate AND e.endDate OR ?2 BETWEEN e.startDate AND e.endDate")
	Long checkEvents(Date startDate, Date endDate);


}
