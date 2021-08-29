package arena.arena.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin extends Person {

	public Admin() {

	}

	public Admin(String firstName, String lastName, String email, String password) {
		super(firstName, lastName, email, password);
	}

	public Admin(Long id, String firstName, String lastName, String email, String password) {
		super(id, firstName, lastName, email, password);
	}
	
	public Admin(Long id, String firstName, String lastName, String email) {
		super(id, firstName, lastName, email);
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

}
