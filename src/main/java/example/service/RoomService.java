package example.service;

import example.dto.RoomDto;
import example.entity.Room;
import example.repository.HotelRepository;
import example.repository.RoomRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class RoomService {
    private final RoomRepository repository;
    private final HotelRepository hotelRepository;

    public List<Room> getAll() {
        return repository.findAll();
    }

    public Room get(Long id) {
        Optional<Room> byId = repository.findById(id);
        return byId.orElse(null);
    }

    public Object add(RoomDto dto) {
        if (repository.existsByFloorAndNumberAndSizeAndHotelId(
                dto.getFloor(),
                dto.getNumber(),
                dto.getSize(),
                dto.getHotelId()
        )) return "the room has already created";
        if (!hotelRepository.existsById(dto.getHotelId())) return "there is no hotel";
        repository.save(
                Room.builder()
                        .floor(dto.getFloor())
                        .number(dto.getNumber())
                        .size(dto.getSize())
                        .hotel(
                                hotelRepository.getById(
                                        dto.getHotelId()
                                )
                        ).build()
        );
        return "successfully added )";
    }

    public Object put(Long id, RoomDto dto) {
        if (!repository.existsById(id)) return "there isn't any room";
        if (!hotelRepository.existsById(dto.getHotelId())) return "there is no hotel";
        if (repository.existsByFloorAndNumberAndSizeAndHotelId(
                dto.getFloor(),
                dto.getNumber(),
                dto.getSize(),
                dto.getHotelId()
        )) return "the room has already created";
        if (!hotelRepository.existsById(dto.getHotelId())) return "there is no hotel";
        repository.save(
                Room.builder()
                        .id(id)
                        .floor(dto.getFloor())
                        .number(dto.getNumber())
                        .size(dto.getSize())
                        .hotel(
                                hotelRepository.getById(
                                        dto.getHotelId()
                                )
                        ).build()
        );
        return "successfully edited )";
    }

    public Object delete(Long id) {
        if (!repository.existsById(id)) return "there isn't any room";
        repository.deleteById(id);
        return "successfully deleted";
    }

    public Object getByHotelId(Long id, Integer page) {
        if (!hotelRepository.existsById(id)) return "the hotel isn't found";
        Pageable pageable = PageRequest.of(page, 10);
        return repository.findAllByHotelId(id, pageable);
    }
}
