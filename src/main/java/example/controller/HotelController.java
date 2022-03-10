package example.controller;

import example.entity.Hotel;
import example.service.HotelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/hotel")
public class HotelController {
    private final HotelService service;

    @GetMapping(value = "/all")
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id){
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Object> add(@RequestBody Hotel hotel){
        return ResponseEntity.ok(service.add(hotel));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> put(@PathVariable Long id, @RequestBody Hotel hotel){
        return ResponseEntity.ok(service.put(id, hotel));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }
}
