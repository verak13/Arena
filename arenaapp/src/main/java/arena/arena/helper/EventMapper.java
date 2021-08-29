package arena.arena.helper;

import java.util.ArrayList;
import java.util.List;

import arena.arena.dto.EventDTO;
import arena.arena.dto.EventResponseDTO;
import arena.arena.model.Event;

public class EventMapper {

	public Event toEntity(EventDTO dto) {
		return new Event(dto.getId(), dto.getName(), dto.getDescription(), dto.getStartDate(), dto.getEndDate(),
				dto.getDeadline(), dto.getParterPrice(), dto.getEastPrice(), dto.getWestPrice(), dto.getNorthPrice(),
				dto.getSouthPrice(), dto.getVipPrice());
	}

	public Event toEntity(EventDTO dto, ArrayList<String> seatNames) {
		return new Event(dto.getId(), dto.getName(), dto.getDescription(), dto.getStartDate(), dto.getEndDate(),
				dto.getDeadline(), dto.getParterPrice(), dto.getEastPrice(), dto.getWestPrice(), dto.getNorthPrice(),
				dto.getSouthPrice(), dto.getVipPrice(), seatNames);
	}

	public EventDTO toDto(Event entity) {
		return new EventDTO(entity.getId(), entity.getName(), entity.getDescription(), entity.getStartDate(),
				entity.getEndDate(), entity.getDeadline(), entity.getParterPrice(), entity.getEastPrice(),
				entity.getWestPrice(), entity.getNorthPrice(), entity.getSouthPrice(), entity.getVipPrice());
	}

	public List<EventDTO> toEventDTOList(List<Event> events) {
		ArrayList<EventDTO> payload = new ArrayList<EventDTO>();
		for (Event event : events) {
			EventDTO DTO = toDto(event);
			payload.add(DTO);
		}
		return payload;
	}

	public EventResponseDTO toDtoWithSeats(Event entity) {
		return new EventResponseDTO(entity.getId(), entity.getName(), entity.getDescription(), entity.getStartDate(),
				entity.getEndDate(), entity.getDeadline(), entity.getParterPrice(), entity.getEastPrice(),
				entity.getWestPrice(), entity.getNorthPrice(), entity.getSouthPrice(), entity.getVipPrice());
	}

}
