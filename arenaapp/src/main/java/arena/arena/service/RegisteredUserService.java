package arena.arena.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import arena.arena.model.RegisteredUser;
import arena.arena.repository.PersonRepository;
import arena.arena.repository.RegisteredUserRepository;


@Service
public class RegisteredUserService {

	@Autowired
	RegisteredUserRepository userRepository;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	EmailService emailService;

	public List<RegisteredUser> findAll() {
		return userRepository.findAll();
	}

	public Page<RegisteredUser> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	public RegisteredUser findOne(Long id) {
		try {
			return userRepository.findById(id).orElseThrow(RuntimeException::new);
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public RegisteredUser findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public RegisteredUser saveOne(RegisteredUser user) {
		try {
			return userRepository.save(user);
		} catch (Exception e) {
			throw e;
		}
	}

	public void delete(Long id) {
		RegisteredUser found;
		try {
			found = userRepository.findById(id).orElseThrow(Exception::new);
			if (found != null) {
				userRepository.delete(found);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public RegisteredUser update(RegisteredUser user) {
		RegisteredUser found = userRepository.findByEmail(user.getEmail());
		if (found != null) {
			found.setFirstName(user.getFirstName());
			found.setLastName(user.getLastName());
			found.setPassword(user.getPassword());
			return userRepository.save(found);
		}
		throw null;
	}

	public RegisteredUser registerUser(RegisteredUser user) {
		RegisteredUser newUser = this.saveOne(user);
		// emailService.sendVerificationMail(newUser.getEmail(), newUser.getId());

		return newUser;
	}

	public RegisteredUser activateAccount(Long id) {
		RegisteredUser found;
		try {
			found = userRepository.findById(id).orElseThrow(Exception::new);
			found.setVerified(true);
			return userRepository.save(found);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		
	}

}
