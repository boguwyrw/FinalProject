package pl.home.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class FilmShow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long filmShowId;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime filmShowDate;

    @ManyToOne // Many FilmShow To One movie
    private Movie movie;

    @ManyToOne
    private Cinema cinema;

    @OneToMany
    private List<Reservation> reservations;

    public FilmShow() {
    }

    public FilmShow(@NotNull LocalDateTime filmShowDate, Movie movie, Cinema cinema) {
        this.filmShowDate = filmShowDate;
        this.movie = movie;
        this.cinema = cinema;
    }
}
