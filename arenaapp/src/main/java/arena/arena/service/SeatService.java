package arena.arena.service;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import arena.arena.model.Seat;
import arena.arena.repository.ReservationRepository;
import arena.arena.repository.SeatRepository;

@Service
@Transactional
public class SeatService {

	@Autowired
	SeatRepository seatRepository;
	
	@Autowired
	ReservationRepository reservationRepository;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Boolean deleteSeats(List<Seat> seats) {
		IntStream.range(0, 200).forEach(x -> {
			Seat s = seatRepository.getById(seats.get(x).getId());
			seatRepository.delete(s);
		});
		return true;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Seat getSeat(Long id) {
		return seatRepository.getById(id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Seat saveSeat(Seat seat) {
		return seatRepository.save(seat);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<Seat> getByEvent(Long eventId) {
		return seatRepository.getByEvent(eventId);
	}
	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<String> getMySeats(Long id, String email) {
		return reservationRepository.getMySeats(id, email);
	}

}
