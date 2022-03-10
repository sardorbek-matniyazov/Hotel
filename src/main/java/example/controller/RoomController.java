package example.controller;

import example.dto.RoomDto;
import example.entity.Room;
import example.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
@AllArgsConstructor
public class RoomController {

    private final RoomService service;

    @GetMapping(value = "/all")
    public List<Room> getAll(){
        return service.getAll();
    }

    @GetMapping(value = "/byHotelId/{hotelId}")
    public ResponseEntity<Object> getByPage(@PathVariable("hotelId") Long id, @RequestParam("page") Integer page){
        return ResponseEntity.ok(service.getByHotelId(id, page));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id){
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Object> add(@RequestBody RoomDto dto){
        return ResponseEntity.ok(service.add(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> put(@PathVariable Long id, @RequestBody RoomDto dto){
        return ResponseEntity.ok(service.put(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }
}
