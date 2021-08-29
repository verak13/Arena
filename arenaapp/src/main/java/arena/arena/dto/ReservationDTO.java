package arena.arena.dto;

import java.util.List;

import javax.validation.constraints.Email;

public class ReservationDTO {

	private Long eventId;
	
	@Email
	private String userEmail;
	
	private List<String> seatNums;

	public ReservationDTO() {
	}

	public ReservationDTO(Long eventId, String userEmail, List<String> seatNums) {
		super();
		this.eventId = eventId;
		this.userEmail = userEmail;
		this.seatNums = seatNums;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public List<String> getSeatNums() {
		return seatNums;
	}

	public void setSeatNums(List<String> seatNums) {
		this.seatNums = seatNums;
	}

}
