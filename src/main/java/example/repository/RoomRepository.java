package example.repository;

import example.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    boolean existsByFloorAndNumberAndSizeAndHotelId(Integer floor, Integer number, Short size, Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM room where hotel_id = ?1",
            nativeQuery = true)
    void deleteAllByHotel_Id(Long id);

    Page<Room> findAllByHotelId(Long id, Pageable pageable);
}
