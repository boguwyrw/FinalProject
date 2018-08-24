package pl.home.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.home.demo.model.Cinema;
import pl.home.demo.service.CinemaService;

import java.util.List;
import java.util.Optional;

@Controller
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @GetMapping(path = "/addCinema")
    public String addCinema(Model model){
        Cinema cinema = new Cinema();
        model.addAttribute("cinema", cinema);
        return "addCinema";
    }

    @PostMapping(path = "/addCinema")
    public String addCinema(Model model, Cinema cinema){
        cinemaService.addCinema(cinema);
        return "redirect:/";
    }

    @GetMapping(path = "/cinemaList")
    public String cinemaList(Model model){
        List<Cinema> cinemaList = cinemaService.getAllCinemas();
        model.addAttribute("cinemaList", cinemaList);
        return "cinemaList";
    }

    @GetMapping(path = "/updateCinema/{cinemaId}")
    public String updateCinema(Model model, @PathVariable(name = "cinemaId") Long cinemaId){
        Optional<Cinema> cinemaOptional = cinemaService.findCinema(cinemaId);
        if(cinemaOptional.isPresent()){
            model.addAttribute("cinema", cinemaOptional.get());
            return "updateCinema";
        }
        return "redirect:/";
    }

    @PostMapping(path = "/updateCinema")
    public String updateCinema(Cinema cinema){
        cinemaService.updateCinema(cinema);
        return "redirect:/cinemaList/";
    }

    @GetMapping(path = "/deleteCinema/{cinemaId}")
    public String deleteCinema(@PathVariable(name = "cinemaId") Long cinemaId){
        cinemaService.deleteCinema(cinemaId);
        return "redirect:/cinemaList";
    }
}
