package arena.arena.helper;

import arena.arena.dto.UserDTO;
import arena.arena.model.RegisteredUser;

public class RegisteredMapper {

	public RegisteredUser toEntity(UserDTO dto) {
		return new RegisteredUser(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getEmail(),
				dto.getPassword());
	}

	public UserDTO toDto(RegisteredUser entity) {
		return new UserDTO(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getEmail());
	}
}
