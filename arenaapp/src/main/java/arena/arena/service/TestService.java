package arena.arena.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import arena.arena.model.Authority;
import arena.arena.model.Event;
import arena.arena.model.RegisteredUser;
import arena.arena.repository.AdminRepository;
import arena.arena.repository.AuthorityRepository;
import arena.arena.repository.EventRepository;
import arena.arena.repository.RegisteredUserRepository;
import arena.arena.repository.SeatRepository;



@Service
@Transactional
public class TestService {
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	SeatRepository seatRepository;
	
	@Autowired
	AuthorityRepository authorityRepo;
	@Autowired
	AdminRepository adminRepo;
	@Autowired
	RegisteredUserRepository userRepo;
	
	public Event add(Event event) {
		return eventRepository.save(event);
	}
	
	@Transactional(readOnly=true)
	public List<Event> getAll() {
		List<Authority> lista = authorityRepo.findAll();
		for (Authority autho : lista) {
			System.out.println(autho.getAuthority());
		}
		System.out.println("u");
		List<RegisteredUser> lista1 = userRepo.findAll();
		for (RegisteredUser autho : lista1) {
			System.out.println(autho.getAuthorities().get(0).getAuthority());
		}
		System.out.println("YES");
		return eventRepository.findAll();
	}

}
