package arena.arena.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import arena.arena.model.Authority;
import arena.arena.repository.AuthorityRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;

	public List<Authority> findById(Long id) {
		List<Authority> auths = new ArrayList<>();
		Authority auth = this.authorityRepository.findById(id).orElse(null);
		if (auth != null)
			auths.add(auth);
		return auths;
	}

}
