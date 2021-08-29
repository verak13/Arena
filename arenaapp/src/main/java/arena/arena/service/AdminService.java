package arena.arena.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import arena.arena.model.Admin;
import arena.arena.repository.AdminRepository;
import arena.arena.repository.PersonRepository;


@Service
public class AdminService {

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	PersonRepository personRepository;

	public List<Admin> findAll() {
		return adminRepository.findAll();
	}

	public Page<Admin> findAll(Pageable pageable) {
		return adminRepository.findAll(pageable);
	}

	public Admin findOne(Long id) {
		try {
			return adminRepository.findById(id).orElseThrow(Exception::new);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Admin findByEmail(String email) {
		return adminRepository.findByEmail(email);
	}

	public Admin saveOne(Admin admin) {
		try {
			return adminRepository.save(admin);
		} catch (Exception e) {
			throw e;
		}
	}

	public void delete(Long id) {
		try {
			Admin found = adminRepository.findById(id).orElseThrow(Exception::new);
			adminRepository.delete(found);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Admin update(Admin admin) {
		Admin found = adminRepository.findByEmail(admin.getEmail());
		if (found != null) {
			found.setFirstName(admin.getFirstName());
			found.setLastName(admin.getLastName());
			found.setPassword(admin.getPassword());
			return adminRepository.save(found);
		}
		return null;
	}

}
