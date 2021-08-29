package arena.arena.helper;

import arena.arena.dto.UserDTO;
import arena.arena.model.Admin;

public class AdminMapper {

    public Admin toEntity(UserDTO dto) {
        return new Admin(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPassword());
    }

    public Admin toEntityWithPass(UserDTO dto) {
        return new Admin(dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getPassword());
    }

    public UserDTO toDto(Admin entity) {
		return new UserDTO(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getEmail());
    }
}
