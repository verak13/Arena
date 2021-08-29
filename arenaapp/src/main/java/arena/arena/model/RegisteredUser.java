package arena.arena.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class RegisteredUser extends Person {

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<Reservation> reservations;

	public RegisteredUser() {
		this.reservations = new ArrayList<Reservation>();
	}

	public RegisteredUser(List<Reservation> reservations) {
		super();
		this.reservations = reservations;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return super.isVerified();
	}

	public RegisteredUser(Long id, String email, String firstName, String lastName, String password) {
		super(id, email, firstName, lastName, password);
	}

	public RegisteredUser(String email, String firstName, String lastName, String password) {
		super(email, firstName, lastName, password);
	}

}
