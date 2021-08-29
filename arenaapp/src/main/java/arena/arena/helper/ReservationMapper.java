package arena.arena.helper;

import arena.arena.dto.ReservationResponseDTO;
import arena.arena.model.Event;
import arena.arena.model.Reservation;

public class ReservationMapper {

	public ReservationResponseDTO toResponseDto(Reservation entity, Event event) {
		String type = entity.getSeatName().split("-")[0];
		Double price = null;
		switch (type) {
		case "PARTER": price = event.getParterPrice(); break;
		case "EAST": price = event.getEastPrice(); break;
		case "WEST": price = event.getWestPrice(); break;
		case "NORTH": price = event.getNorthPrice(); break;
		case "SOUTH": price = event.getSouthPrice(); break;
		case "VIP": price = event.getVipPrice(); break;
		default: price = null; break;
		}
		return new ReservationResponseDTO(entity.getId(), event.getId(), event.getName(), event.getStartDate(),
				event.getEndDate(), event.getDeadline(), price, entity.getSeatName(),
				entity.getActive(), entity.getEmail());
	}

}
