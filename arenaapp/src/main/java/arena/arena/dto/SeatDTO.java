package arena.arena.dto;

public class SeatDTO {

	private Long id;
	
	private String number;
	
	private Long version;
	
	private Boolean occupied;
	
	private Boolean occupiedByMe;

	public SeatDTO() {
	}

	public SeatDTO(Long id, String number, Long version, Boolean occupied, Boolean occupiedByMe) {
		super();
		this.id = id;
		this.number = number;
		this.version = version;
		this.occupied = occupied;
		this.occupiedByMe = occupiedByMe;
	}
	
	public SeatDTO(Long id, Long version, Boolean occupied, Boolean occupiedByMe) {
		super();
		this.id = id;
		this.version = version;
		this.occupied = occupied;
		this.occupiedByMe = occupiedByMe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Boolean getOccupied() {
		return occupied;
	}

	public void setOccupied(Boolean occupied) {
		this.occupied = occupied;
	}

	public Boolean getOccupiedByMe() {
		return occupiedByMe;
	}

	public void setOccupiedByMe(Boolean occupiedByMe) {
		this.occupiedByMe = occupiedByMe;
	}
	
	public String getNumber() {
		return this.number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}

}
