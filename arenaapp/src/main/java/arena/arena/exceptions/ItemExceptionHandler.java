package arena.arena.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ItemExceptionHandler {

	@ExceptionHandler(ReservationException.class)
	public ResponseEntity<String> handleReservationException(ReservationException exception) {
		return ResponseEntity.badRequest().body(exception.getMessage());
	}
}
