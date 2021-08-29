package arena.arena.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservations")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "event_id", referencedColumnName = "id")
	private Event event;

	@Column(name = "seat_name")
	private String seatName;

	@Column(name = "active")
	private Boolean active;

	@Column(name = "email")
	private String email;

	public Reservation() {
		this.active = true;
	}

	public Reservation(Long id, Event event, String seatName, Boolean active, String email) {
		super();
		this.id = id;
		this.event = event;
		this.seatName = seatName;
		this.active = active;
		this.email = email;
	}

	public Reservation(Event event, String seatName, Boolean active, String email) {
		super();
		this.event = event;
		this.seatName = seatName;
		this.active = active;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getSeatName() {
		return seatName;
	}

	public void setSeatName(String seatName) {
		this.seatName = seatName;
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
