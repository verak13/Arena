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
import arena.arena.helper.AdminMapper;
import arena.arena.model.Admin;
import arena.arena.service.AdminService;

@RestController
@RequestMapping(value = "/administrators", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

	@Autowired
	AdminService adminService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationController authController;

	private final AdminMapper adminMapper = new AdminMapper();

	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateRegistered(@Valid @RequestBody UserDTO regDTO) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Admin regLogged = (Admin) authentication.getPrincipal();

		if (!regDTO.getEmail().equals(regLogged.getEmail()) || regDTO.getId() != regLogged.getId()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		String password = regDTO.getPassword();
		regDTO.setPassword(regDTO.getPassword().equals("________") ? regLogged.getPassword()
				: passwordEncoder.encode(regDTO.getPassword()));
		Admin reg = adminService.update(adminMapper.toEntity(regDTO));

		if (reg == null) {
			return new ResponseEntity<>("Email already exists.", HttpStatus.BAD_REQUEST);
		}

		if (!password.equals("________")) {
			authController.updatedLoggedIn(reg.getUsername(), password);
		}

		return new ResponseEntity<>(adminMapper.toDto(reg), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getRegistered(@PathVariable Long id) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Admin regLogged = (Admin) authentication.getPrincipal();

		Admin reg = adminService.findOne(id);

		if (reg == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		} else if (reg.getUsername().equals(regLogged.getUsername())) {
			UserDTO regDTO = adminMapper.toDto(reg);
			return new ResponseEntity<>(regDTO, HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

	}

}
