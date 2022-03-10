package example.service;

import example.entity.Hotel;
import example.repository.HotelRepository;
import example.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HotelService {
    private final HotelRepository repository;
    private final RoomRepository roomRepository;

    public List<Hotel> getAll() {
        return repository.findAll();
    }

    public Object get(Long id) {
        Optional<Hotel> byId = repository.findById(id);
        return byId.orElse(null);
    }

    public Object add(Hotel hotel) {
        return repository.save(hotel);
    }

    public Object put(Long id, Hotel hotel) {
        Optional<Hotel> byId = repository.findById(id);
        if (byId.isPresent()){
            Hotel hotel1 = byId.get();
            hotel1.setName(hotel.getName());
            return repository.save(hotel1);
        }
        return "The hotel isn't find !";
    }

    public Object delete(Long id) {
        if (!repository.existsById(id)) return "there isn't any hotel";
        roomRepository.deleteAllByHotel_Id(id);
        repository.deleteById(id);
        return null;
    }
}
