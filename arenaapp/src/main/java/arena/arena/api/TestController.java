package arena.arena.api;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import arena.arena.model.Event;
import arena.arena.service.EmailService;
import arena.arena.service.TestService;

@RestController
@RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
public class TestController {

	@Autowired
	TestService eventService;

	@Autowired
	EmailService emailService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getEvents() {

		try {
			return new ResponseEntity<>(eventService.getAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Event> postEvent(@RequestBody Event event) {

		return new ResponseEntity<>(eventService.add(event), HttpStatus.OK);

	}

	@RequestMapping(value = "/try", method = RequestMethod.GET)
	public ResponseEntity<?> tryy() {
		try {
			emailService.testEmail();
		} catch (MailException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.OK);

	}

}
