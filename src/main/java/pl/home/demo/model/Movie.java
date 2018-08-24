package pl.home.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long movieId;

    @NotNull
    private String title;
    @NotNull
    private String director;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate releaseDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime startHour;

    @NotNull
    private String filmGenre;
    @NotNull
    private Integer ageRating;
/*
    @OneToMany // jeden film do wielu rezerwacji
    private List<Reservation> reservations;
*/
    @OneToMany
    private List<FilmShow> filmShows;
}
