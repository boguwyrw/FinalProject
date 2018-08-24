package pl.home.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reservationId;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    @ManyToOne
    private FilmShow filmShow;
/*
    @ManyToOne // Many Reservations To One movie
    private Movie movie;

    @ManyToOne
    private Cinema cinema;
*/
}