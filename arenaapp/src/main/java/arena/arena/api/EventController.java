package arena.arena.api;

import javax.validation.Valid;

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

import arena.arena.dto.EventDTO;
import arena.arena.dto.EventResponseDTO;
import arena.arena.model.Event;
import arena.arena.model.Person;
import arena.arena.service.EventService;

@RestController
@RequestMapping(value = "/events", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventController {

	@Autowired
	EventService eventService;

	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR') || hasRole('ROLE_USER')")
	@RequestMapping(value = "/by-page", method = RequestMethod.GET)
	public ResponseEntity<Page<EventDTO>> getEvents(Pageable pageable) {
		try {
			return new ResponseEntity<>(eventService.getEvents(pageable), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR') || hasRole('ROLE_USER')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<EventResponseDTO> getEvent(@PathVariable("id") Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		try {
			return new ResponseEntity<>(eventService.getEvent(id, ((Person) authentication.getPrincipal()).getEmail()),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createEvent(@Valid @RequestBody EventDTO event) {
		try {
			Event result = eventService.addEvent(event);
			if (result != null)
				return new ResponseEntity<>(result, HttpStatus.OK);
			else
				return new ResponseEntity<>("Unsuccessful. Check if your dates are ok.", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
	@RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateEvent(@Valid @RequestBody EventDTO event) {
		try {
			Event result = eventService.updateEvent(event);
			if (result != null)
				return new ResponseEntity<>(result, HttpStatus.OK);
			else
				return new ResponseEntity<>("Unsuccessful. Check if your dates are ok.", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteEvent(@PathVariable("id") Long id) {
		try {
			Boolean result = eventService.deleteEvent(id);
			if (result)
				return new ResponseEntity<>(result, HttpStatus.OK);
			else
				return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
