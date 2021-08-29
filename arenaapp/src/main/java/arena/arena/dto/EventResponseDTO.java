package arena.arena.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventResponseDTO {

	private Long id;
	
	private String name;
	
	private String description;
	
	private Date startDate;
	
	private Date endDate;
	
	private Date deadline;
	
	private Double parterPrice;
	
	private Double eastPrice;
	
	private Double westPrice;
	
	private Double northPrice;
	
	private Double southPrice;
	
	private Double vipPrice;
	
	private List<SeatDTO> seats;

	public EventResponseDTO() {
	}

	public EventResponseDTO(Long id, String name, String description, Date startDate, Date endDate, Date deadline,
			Double parterPrice, Double eastPrice, Double westPrice, Double northPrice,
			Double southPrice, Double vipPrice, List<SeatDTO> seats) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.deadline = deadline;
		this.parterPrice = parterPrice;
		this.eastPrice = eastPrice;
		this.westPrice = westPrice;
		this.northPrice = northPrice;
		this.southPrice = southPrice;
		this.vipPrice = vipPrice;
		this.seats = seats;
	}

	public EventResponseDTO(Long id, String name, String description, Date startDate, Date endDate, Date deadline,
			Double parterPrice, Double eastPrice, Double westPrice, Double northPrice,
			Double southPrice, Double vipPrice) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.parterPrice = parterPrice;
		this.eastPrice = eastPrice;
		this.westPrice = westPrice;
		this.northPrice = northPrice;
		this.southPrice = southPrice;
		this.vipPrice = vipPrice;
		this.deadline = deadline;
		this.seats = new ArrayList<SeatDTO>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Double getParterPrice() {
		return parterPrice;
	}

	public void setParterPrice(Double parterPrice) {
		this.parterPrice = parterPrice;
	}

	public Double getEastPrice() {
		return eastPrice;
	}

	public void setEastPrice(Double eastPrice) {
		this.eastPrice = eastPrice;
	}

	public Double getWestPrice() {
		return westPrice;
	}

	public void setWestPrice(Double westPrice) {
		this.westPrice = westPrice;
	}

	public Double getNorthPrice() {
		return northPrice;
	}

	public void setNorthPrice(Double northPrice) {
		this.northPrice = northPrice;
	}

	public Double getSouthPrice() {
		return southPrice;
	}

	public void setSouthPrice(Double southPrice) {
		this.southPrice = southPrice;
	}

	public Double getVipPrice() {
		return vipPrice;
	}

	public void setVipPrice(Double vipPrice) {
		this.vipPrice = vipPrice;
	}

	public List<SeatDTO> getSeats() {
		return seats;
	}

	public void setSeats(List<SeatDTO> seats) {
		this.seats = seats;
	}

}
