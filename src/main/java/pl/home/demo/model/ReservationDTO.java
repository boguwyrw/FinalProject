package pl.home.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    private String firstName;
    private String lastName;

    private Long filmShowId;

    private Long reservationId;
}
