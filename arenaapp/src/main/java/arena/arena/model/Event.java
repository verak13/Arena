package arena.arena.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "events")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "deadline")
	private Date deadline;

	@Column(name = "parter_price")
	private Double parterPrice;
	
	@Column(name = "east_price")
	private Double eastPrice;
	
	@Column(name = "west_price")
	private Double westPrice;
	
	@Column(name = "north_price")
	private Double northPrice;
	
	@Column(name = "south_price")
	private Double southPrice;
	
	@Column(name = "vip_price")
	private Double vipPrice;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "event_id")
	private List<Seat> seats;

	public Event() {
		this.seats = IntStream.range(0, 2000).mapToObj(x -> new Seat()).collect(Collectors.toList());
	}
	
	public Event(ArrayList<String> names) {
		this.seats = IntStream.range(0, 2000).mapToObj(x -> new Seat(names.get(x))).collect(Collectors.toList());
	}

	public Event(Long id, String name, String description, Date startDate, Date endDate, Date deadline,
			Double parterPrice, Double eastPrice, Double westPrice, Double northPrice, Double southPrice,
			Double vipPrice) {
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
		this.seats = IntStream.range(0, 2000).mapToObj(x -> new Seat()).collect(Collectors.toList());
	}

	public Event(Long id, String name, String description, Date startDate, Date endDate, Date deadline,
			Double parterPrice, Double eastPrice, Double westPrice, Double northPrice, Double southPrice,
			Double vipPrice, ArrayList<String> names) {
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
		this.seats = IntStream.range(0, 2000).mapToObj(x -> new Seat(names.get(x))).collect(Collectors.toList());
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

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
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
	
	

}
