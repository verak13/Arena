package arena.arena.dto;

import java.util.Date;

public class ReservationResponseDTO {

	private Long id;
	
	private Long eventId;
	
	private String eventName;
	
	private Date startDate;
	
	private Date endDate;
	
	private Date deadline;
	
	private Double seatPriceRsd;
	
	private String seatNum;
	
	private Boolean active;
	
	private String email;

	public ReservationResponseDTO() {
	}

	public ReservationResponseDTO(Long id, Long eventId, String eventName, Date startDate, Date endDate, Date deadline,
			Double seatPriceRsd, String seatNum, Boolean active, String email) {
		super();
		this.id = id;
		this.eventId = eventId;
		this.eventName = eventName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.deadline = deadline;
		this.seatPriceRsd = seatPriceRsd;
		this.seatNum = seatNum;
		this.active = active;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Double getSeatPriceRsd() {
		return seatPriceRsd;
	}

	public void setSeatPriceRsd(Double seatPriceRsd) {
		this.seatPriceRsd = seatPriceRsd;
	}

	public String getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
