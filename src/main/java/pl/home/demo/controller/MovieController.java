package pl.home.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.home.demo.model.Movie;
import pl.home.demo.service.MovieService;

import javax.naming.directory.SearchResult;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static jdk.nashorn.internal.objects.NativeArray.sort;

@Controller
public class MovieController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String testPage(){
        return "mainPage";
    }


    @Autowired
    private MovieService movieService;

    @GetMapping(path = "/addMovie")
    public String addMovie(Model model){
        Movie movie = new Movie();
        model.addAttribute("movie", movie);
        return "addMovie";
    }


    @PostMapping(path = "/addMovie")
    public String addMovie(Model model, Movie movie){
        movieService.addMovie(movie);
        return "redirect:/";
    }

    @GetMapping(path = "/movieList")
    public String movieList(Model model){
        List<Movie> movieList = movieService.getAllMovies();
        model.addAttribute("movieList", movieList);
        return "movieList";
    }

    @GetMapping(path = "/updateMovie/{movieId}")
    public String updateMovie(Model model, @PathVariable(name = "movieId") Long movieId){
        Optional<Movie> movieOptional = movieService.findMovie(movieId);
        if(movieOptional.isPresent()){
            model.addAttribute("movie", movieOptional.get());
            return "updateMovie";
        }
        return "redirect:/";
    }

    @PostMapping(path = "/updateMovie")
    public String updateMovie(Movie movie){
        movieService.updateMovie(movie);
        return "redirect:/movieList/";
    }

    @GetMapping(path = "/deleteMovie/{movieId}")
    public String deleteMovie(@PathVariable(name = "movieId") Long movieId){
        movieService.deleteMovie(movieId);
        return "redirect:/movieList";
    }

    @GetMapping(path = "/findMovieByTitle")
    public String findMovieByTitle(Model model, @RequestParam(name = "movieTitle") String movieTitle){
        model.addAttribute("movieList", movieService.findAllByTitleContainingString(movieTitle));
        return "movieList";
    }

    @GetMapping(path = "/sort")
    public String sort(Model model, @RequestParam(name="asc") boolean asc){
        model.addAttribute("movieList", movieService.sortName(asc));
        return "movieList";
    }
}
