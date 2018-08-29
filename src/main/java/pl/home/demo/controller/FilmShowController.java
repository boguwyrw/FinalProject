package pl.home.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.home.demo.model.Cinema;
import pl.home.demo.model.FilmShow;
import pl.home.demo.model.FilmShowDTO;
import pl.home.demo.model.Movie;
import pl.home.demo.service.CinemaService;
import pl.home.demo.service.FilmShowService;
import pl.home.demo.service.MovieService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class FilmShowController {

    @Autowired
    private FilmShowService filmShowService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private CinemaService cinemaService;

    @GetMapping(path = "/addFilmShow")
    public String addFilmShow(Model model){

        FilmShowDTO filmShowDTO = new FilmShowDTO();
        model.addAttribute("filmShowDTO", filmShowDTO);

        List<Movie> movies = new ArrayList<>();
        movies.addAll(movieService.getAllMovies());
        model.addAttribute("movies", movies);

        List<Cinema> cinemas = new ArrayList<>();
        cinemas.addAll(cinemaService.getAllCinemas());
        model.addAttribute("cinemas", cinemas);

        return "addFilmShow";
    }

    @PostMapping(path = "/addFilmShow")
    public String addFilmShow(Model model, FilmShowDTO filmShowDTO){

        Optional<Movie> movieOptional = movieService.findMovie(filmShowDTO.getMovieId());
        Optional<Cinema> cinemaOptional = cinemaService.findCinema(filmShowDTO.getCinemaId());

        FilmShow filmShow = new FilmShow(filmShowDTO.getFilmShowDate(), movieOptional.get(), cinemaOptional.get());

        filmShowService.addFilmShow(filmShow);
        return "redirect:/";
    }

    @GetMapping(path = "/filmShowList")
    public String filmShowList(Model model){
        List<FilmShow> filmShowList = filmShowService.getAllFilmShow();
        model.addAttribute("filmShowList", filmShowList);
        return "filmShowList";
    }

    @GetMapping(path = "/deleteFilmShow/{filmShowId}")
    public String deleteFilmShow(@PathVariable(name = "filmShowId") Long filmShowId){
        filmShowService.deleteFilmShow(filmShowId);
        return "redirect:/filmShowList";
    }

    @GetMapping(path = "/updateFilmShow/{filmShowId}")
    public String updateFilmShow(Model model, @PathVariable(name = "filmShowId") Long filmShowId){
        Optional<FilmShow> filmShowOptional = filmShowService.findFilmShow(filmShowId);
        if(filmShowOptional.isPresent()){
            FilmShow filmShow = filmShowOptional.get();
            FilmShowDTO filmShowDTO = new FilmShowDTO(filmShow.getFilmShowDate(), filmShow.getMovie().getMovieId(), filmShow.getCinema().getCinemaId(), filmShowId);
            model.addAttribute("filmShowDTO", filmShowDTO);

            List<Movie> movies = new ArrayList<>();
            movies.addAll(movieService.getAllMovies());
            model.addAttribute("movies", movies);

            List<Cinema> cinemas = new ArrayList<>();
            cinemas.addAll(cinemaService.getAllCinemas());
            model.addAttribute("cinemas", cinemas);

            return "updateFilmShow";
        }
        return "redirect:/";
    }

    @PostMapping(path = "/updateFilmShow")
    public String updateFilmShow(FilmShowDTO filmShowDTO){

        Optional<Movie> movieOptional = movieService.findMovie(filmShowDTO.getMovieId());
        Optional<Cinema> cinemaOptional = cinemaService.findCinema(filmShowDTO.getCinemaId());

        // pobieramy z bazy danych film show ktory chcemy edytowac
        Optional<FilmShow> editedFilmShow = filmShowService.findFilmShow(filmShowDTO.getFilmShowId());
        FilmShow filmShow = editedFilmShow.get();
//        FilmShow filmShow = new FilmShow(filmShowDTO.getFilmShowDate(), movieOptional.get(), cinemaOptional.get());

        // ustawiam nowe (zmienione rzeczy) na film show
        filmShow.setCinema(cinemaOptional.get());
        filmShow.setMovie(movieOptional.get());
        filmShow.setFilmShowDate(filmShowDTO.getFilmShowDate());

        // wysylam zmiany do bazy
        filmShowService.updateFilmShow(filmShow);
        return "redirect:/filmShowList/";
    }
/*
    @GetMapping(path = "/sort")
    public String sort(Model model, @RequestParam(name="asc") boolean asc){
        model.addAttribute("movieList", movieService.sortName(asc));
        return "movieList";
    }
*/
/*
    @GetMapping(path = "/sortFilmShow")
    public String sort(Model model, @RequestParam(name="asc") boolean asc){
        model.addAttribute("filmShowList", filmShowService.sortName(asc));
        return "filmShowList";
    }
*/
}
