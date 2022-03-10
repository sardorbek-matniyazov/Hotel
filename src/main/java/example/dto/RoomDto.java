package example.dto;

import lombok.Data;

@Data
public class RoomDto {
    private Integer number;
    private Integer floor;
    private Short size;
    private Long hotelId;
}
