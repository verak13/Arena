package arena.arena.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "seats")
public class Seat implements Comparable<Seat> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "row_generator")
	@SequenceGenerator(name = "row_generator", sequenceName = "db_generator", allocationSize = 500)
	private Long id;
	
	@Column(name = "seat_name")
	private String seatName;

	@Column(name = "occupied")
	private Boolean occupied;

	@Version
	@Column(name = "version")
	private Long version;

	public Seat() {
		this.occupied = false;
	}
	
	public Seat(String name) {
		this.occupied = false;
		this.seatName = name;
	}
	
	public Seat(Long id, String name) {
		this.occupied = false;
		this.seatName = name;
		this.id = id;
	}

	public Seat(Long id, Boolean occupied, Long version) {
		super();
		this.id = id;
		this.occupied = occupied;
		this.version = version;
	}
	
	public Seat(Long id, Boolean occupied, Long version, String seatName) {
		super();
		this.id = id;
		this.occupied = occupied;
		this.version = version;
		this.seatName = seatName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getOccupied() {
		return occupied;
	}

	public void setOccupied(Boolean occupied) {
		this.occupied = occupied;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getSeatName() {
		return this.seatName;
	}
	
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	
	@Override
	public int compareTo(Seat s) {
		return getId().compareTo(s.getId());
	}
}
