package arena.arena.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import arena.arena.dto.ReservationDTO;
import arena.arena.dto.ReservationResponseDTO;
import arena.arena.model.RegisteredUser;
import arena.arena.service.ReservationService;

@RestController
@RequestMapping(value = "/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {

	@Autowired
	ReservationService reservationService;

	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
	@RequestMapping(value = "/all/by-page", method = RequestMethod.GET)
	public ResponseEntity<Page<ReservationResponseDTO>> getAllReservations(Pageable pageable) {
		try {
			return new ResponseEntity<>(reservationService.getAllReservations(pageable), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/my/by-page", method = RequestMethod.GET)
	public ResponseEntity<Page<ReservationResponseDTO>> getMyReservations(Pageable pageable) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			RegisteredUser person = (RegisteredUser) authentication.getPrincipal();
			return new ResponseEntity<>(reservationService.getMyReservations(pageable, person.getEmail()),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
	@RequestMapping(value = "/by-event/{id}/by-page", method = RequestMethod.GET)
	public ResponseEntity<Page<ReservationResponseDTO>> getReresvationsByEvent(@PathVariable("id") Long eventId, Pageable pageable) {
		try {
			return new ResponseEntity<>(reservationService.getReservationsByEvent(pageable, eventId),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR') || hasRole('ROLE_USER')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ReservationResponseDTO> getMyReservation(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<>(reservationService.getMyReservation(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/make-reservation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> makeReservation(@RequestBody ReservationDTO dto) {
		List<String> result = reservationService.makeReservations(dto);
		if (result != null)
			return new ResponseEntity<>(result, HttpStatus.OK);
		else
			return new ResponseEntity<>(
					"Reservation has failed. The seats you marked have been occupied in the meantime.",
					HttpStatus.BAD_REQUEST);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/cancel-reservation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> cancelReservation(@RequestBody ReservationDTO dto) {
		List<String> result = reservationService.cancelReservations(dto);
		if (result != null)
			return new ResponseEntity<>(result, HttpStatus.OK);
		else
			return new ResponseEntity<>("Cancellation has failed.", HttpStatus.BAD_REQUEST);
	}

}
