package arena.arena.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.OptimisticLockException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import arena.arena.dto.ReservationDTO;
import arena.arena.dto.ReservationResponseDTO;
import arena.arena.exceptions.ReservationException;
import arena.arena.helper.ReservationMapper;
import arena.arena.model.Event;
import arena.arena.model.RegisteredUser;
import arena.arena.model.Reservation;
import arena.arena.model.Seat;
import arena.arena.repository.ReservationRepository;

@Service
@Transactional
public class ReservationService {

	private final ReservationMapper reservationMapper = new ReservationMapper();
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	SeatService seatService;

	@Autowired
	EventService eventService;

	@Autowired
	RegisteredUserService regService;

	@Autowired
	EmailService emailService;

	@Transactional(readOnly = true)
	public Page<ReservationResponseDTO> getAllReservations(Pageable pageable) {
		Page<Reservation> page = reservationRepository.findAll(pageable);
		List<ReservationResponseDTO> payload = new ArrayList<ReservationResponseDTO>();
		for (Reservation res : page) {
			Event e = eventService.getById(res.getEvent().getId());
			ReservationResponseDTO DTO = reservationMapper.toResponseDto(res, e);
			payload.add(DTO);
		}
		return new PageImpl<>(payload, page.getPageable(), page.getTotalElements());
	}

	@Transactional(readOnly = true)
	public Page<ReservationResponseDTO> getMyReservations(Pageable pageable, String myEmail) {
		Page<Reservation> page = reservationRepository.getByEmail(myEmail, pageable);
		List<ReservationResponseDTO> payload = new ArrayList<ReservationResponseDTO>();
		for (Reservation res : page) {
			Event e = eventService.getById(res.getEvent().getId());
			ReservationResponseDTO DTO = reservationMapper.toResponseDto(res, e);
			payload.add(DTO);
		}
		return new PageImpl<>(payload, page.getPageable(), page.getTotalElements());
	}

	@Transactional(readOnly = true)
	public Page<ReservationResponseDTO> getReservationsByEvent(Pageable pageable, Long eventId) {
		Page<Reservation> page = reservationRepository.getByEvent(eventId, pageable);
		List<ReservationResponseDTO> payload = new ArrayList<ReservationResponseDTO>();
		for (Reservation res : page) {
			Event e = eventService.getById(res.getEvent().getId());
			ReservationResponseDTO DTO = reservationMapper.toResponseDto(res, e);
			payload.add(DTO);
		}
		return new PageImpl<>(payload, page.getPageable(), page.getTotalElements());
	}

	@Transactional(readOnly = true)
	public ReservationResponseDTO getMyReservation(Long id) {
		Reservation res = reservationRepository.getById(id);
		Event e = eventService.getById(res.getEvent().getId());
		return reservationMapper.toResponseDto(res, e);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<String> makeReservations(ReservationDTO dto) {
		Event e = eventService.getById(dto.getEventId());

		Collections.sort(e.getSeats());
		if (e.getDeadline().before(new Date())) {
			throw new ReservationException("You cannot make a reservation after " + sdf.format(e.getDeadline()));
		}
		if (reservationRepository.checkReserved(dto.getUserEmail(), e.getId()) + dto.getSeatNums().size() > 4L) {
			throw new ReservationException("You cannot make more than 4 reservations for one event.");
		}
		List<String> reserved = new ArrayList<String>();
		List<Double> prices = new ArrayList<Double>();
		for (String seat : dto.getSeatNums()) {
			try {
				String type = seat.split("-")[0];
				Double price = null;
				switch (type) {
				case "PARTER":
					price = e.getParterPrice();
					break;
				case "EAST":
					price = e.getEastPrice();
					break;
				case "WEST":
					price = e.getWestPrice();
					break;
				case "NORT":
					price = e.getNorthPrice();
					break;
				case "SOUTH":
					price = e.getSouthPrice();
					break;
				case "VIP":
					price = e.getVipPrice();
					break;
				default:
					price = null;
					break;
				}

				Seat temp = e.getSeats().stream().filter(p -> p.getSeatName().equals(seat)).findFirst()
						.orElseThrow(Exception::new);
				Seat s = seatService.getSeat(temp.getId());
				if (!s.getOccupied()) {
					s.setOccupied(true);
					seatService.saveSeat(s);
					Reservation r = new Reservation(e, seat, true, dto.getUserEmail());
					RegisteredUser reg = regService.findByEmail(dto.getUserEmail());
					reg.getReservations().add(r);
					reservationRepository.save(r);
					regService.saveOne(reg);
					reserved.add(seat);
					prices.add(price);
				}
			} catch (OptimisticLockException o) {
				o.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		try {
			if (!reserved.isEmpty()) {
				emailService.makeReservationEmail(dto.getUserEmail(), e.getName(), e.getStartDate(), prices, reserved);
				return reserved;
			} else {
				return null;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<String> cancelReservations(ReservationDTO dto) {
		Event e = eventService.getById(dto.getEventId());
		Collections.sort(e.getSeats());
		if (e.getDeadline().before(new Date())) {
			throw new ReservationException("You cannot cancel reservation after " + sdf.format(e.getDeadline()));
		}
		List<String> cancelled = new ArrayList<String>();
		for (String seat : dto.getSeatNums()) {
			try {
				Seat temp = e.getSeats().stream().filter(p -> p.getSeatName().equals(seat)).findFirst()
						.orElseThrow(Exception::new);
				Seat s = seatService.getSeat(temp.getId());
				Reservation r = reservationRepository.findByEventAndSeatAndEmail(e.getId(), seat, dto.getUserEmail());
				if (!r.getEmail().equals(dto.getUserEmail())) {
					throw new ReservationException("Wrong email address.");
				}
				s.setOccupied(false);
				seatService.saveSeat(s);
				r.setActive(false);
				reservationRepository.save(r);
				cancelled.add(seat);
			} catch (OptimisticLockException o) {
				o.printStackTrace();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		try {
			if (!cancelled.isEmpty()) {
				emailService.cancelReservationEmail(dto.getUserEmail(), e.getName(), e.getStartDate(), cancelled);
				return cancelled;
			} else {
				return null;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}

}
