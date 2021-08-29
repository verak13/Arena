package arena.arena.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import arena.arena.dto.UserDTO;
import arena.arena.helper.RegisteredMapper;
import arena.arena.model.RegisteredUser;
import arena.arena.service.RegisteredUserService;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	RegisteredUserService regService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationController authController;

	private final RegisteredMapper regMapper = new RegisteredMapper();

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateRegistered(@Valid @RequestBody UserDTO regDTO) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		RegisteredUser regLogged = (RegisteredUser) authentication.getPrincipal();

		if (!regDTO.getEmail().equals(regLogged.getEmail()) || regDTO.getId() != regLogged.getId()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		String password = regDTO.getPassword();
		regDTO.setPassword(regDTO.getPassword().equals("________") ? regLogged.getPassword()
				: passwordEncoder.encode(regDTO.getPassword()));
		RegisteredUser reg = regService.update(regMapper.toEntity(regDTO));

		if (reg == null) {
			return new ResponseEntity<>("Email already exists.", HttpStatus.BAD_REQUEST);
		}

		if (!password.equals("________")) {
			authController.updatedLoggedIn(reg.getUsername(), password);
		}

		return new ResponseEntity<>(regMapper.toDto(reg), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getRegistered(@PathVariable Long id) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		RegisteredUser regLogged = (RegisteredUser) authentication.getPrincipal();

		RegisteredUser reg = regService.findOne(id);

		if (reg == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		} else if (reg.getUsername().equals(regLogged.getUsername())) {
			UserDTO regDTO = regMapper.toDto(reg);
			return new ResponseEntity<>(regDTO, HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

	}

}
