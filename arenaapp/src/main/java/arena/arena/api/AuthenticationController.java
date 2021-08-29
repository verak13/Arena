package arena.arena.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import arena.arena.dto.UserDTO;
import arena.arena.dto.UserLoginDTO;
import arena.arena.dto.UserTokenStateDTO;
import arena.arena.helper.RegisteredMapper;
import arena.arena.model.Admin;
import arena.arena.model.Authority;
import arena.arena.model.Person;
import arena.arena.model.RegisteredUser;
import arena.arena.security.TokenUtils;
import arena.arena.service.AdminService;
import arena.arena.service.AuthorityService;
import arena.arena.service.CustomUserDetailsService;
import arena.arena.service.RegisteredUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

//123qweASD
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private RegisteredUserService regService;

	@Autowired
	private AdminService adminService;

	@Autowired
	AuthorityService authorityService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private RegisteredMapper regMapper;

	public AuthenticationController() {
		regMapper = new RegisteredMapper();
	}

	// Prvi endpoint koji pogadja korisnik kada se loguje.
	// Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
	@PostMapping("/log-in")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserLoginDTO authenticationRequest,
			HttpServletResponse response) {

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getEmail(), authenticationRequest.getPassword()));

		// Ubaci korisnika u trenutni security kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token za tog korisnika
		Person person = (Person) authentication.getPrincipal();
		String jwt = tokenUtils.generateToken(person.getUsername(), person.getId(),
				person.getAuthorities().get(0).getAuthority()); // prijavljujemo se na sistem sa email adresom

		// Vrati token kao odgovor na uspesnu autentifikaciju
		return ResponseEntity.ok(new UserTokenStateDTO(jwt));
	}

	// Endpoint za registraciju novog korisnika
	@PostMapping("/sign-up")
	public ResponseEntity<?> signUp(@Valid @RequestBody UserDTO userRequest) throws Exception {

		RegisteredUser existUser = regService.findByEmail(userRequest.getEmail());
		Admin existAdmin = adminService.findByEmail(userRequest.getEmail());
		if (existUser != null || existAdmin != null) {
			return new ResponseEntity<>("Email already exists.", HttpStatus.BAD_REQUEST);
		}
		existUser = regMapper.toEntity(userRequest);
		existUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));

		Long role = 2L;
		List<Authority> auth = authorityService.findById(role);
		existUser.setAuthorities(auth);
		existUser.setVerified(true);

		RegisteredUser newUSer = regService.registerUser(existUser);

		if (newUSer == null) {
			return new ResponseEntity<>("Email already exists.", HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(regMapper.toDto(newUSer), HttpStatus.CREATED);
	}

	// U slucaju isteka vazenja JWT tokena, endpoint koji se poziva da se token
	// osvezi
	@PostMapping(value = "/refresh")
	public ResponseEntity<UserTokenStateDTO> refreshAuthenticationToken(HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		String email = this.tokenUtils.getEmailFromToken(token);
		Person person = (Person) this.userDetailsService.loadUserByUsername(email);

		if (this.tokenUtils.canTokenBeRefreshed(token, person.getLastPasswordResetDate())) {
			String refreshedToken = tokenUtils.refreshToken(token);
			int expiresIn = tokenUtils.getExpiredIn();

			return ResponseEntity.ok(new UserTokenStateDTO(refreshedToken));
		} else {
			UserTokenStateDTO userTokenState = new UserTokenStateDTO();
			return ResponseEntity.badRequest().body(userTokenState);
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR') || hasRole('ROLE_USER')")
	public void updatedLoggedIn(String email, String password) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(email, password));

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	@PostMapping("/activate/{id}")
	public ResponseEntity<?> activate(@PathVariable Long id) {
		RegisteredUser regUser = regService.activateAccount(id);

		if (regUser == null)
			return new ResponseEntity<>("Activation failed.", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
