package pl.home.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.home.demo.model.FilmShow;
import pl.home.demo.model.Reservation;
import pl.home.demo.model.ReservationDTO;
import pl.home.demo.service.FilmShowService;
import pl.home.demo.service.ReservationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private FilmShowService filmShowService;

    @GetMapping(path = "/addReservation")
    public String addReservation(Model model) {
        ReservationDTO reservationDTO = new ReservationDTO();
        model.addAttribute("reservationDTO", reservationDTO);

        List<FilmShow> filmShows = new ArrayList<>();
        filmShows.addAll(filmShowService.getAllFilmShow());
        model.addAttribute("filmShows", filmShows);

        return "addReservation";
    }

    @PostMapping(path = "/addReservation")
    public String addReservation(Model model, ReservationDTO reservationDTO) {
        Optional<FilmShow> filmShowOptional = filmShowService.findFilmShow(reservationDTO.getFilmShowId());

        Reservation newReservation = new Reservation(null,
                reservationDTO.getFirstName(),
                reservationDTO.getLastName(),
                filmShowOptional.get());

        reservationService.addReservation(newReservation);
        return "redirect:/";
    }

    @GetMapping(path = "/reservationList")
    public String reservationList(Model model) {
        List<Reservation> reservationList = reservationService.getAllReservations();
        model.addAttribute("reservationList", reservationList);
        return "reservationList";
    }

    @GetMapping(path = "/deleteReservation/{reservationId}")
    public String deleteReservation(@PathVariable(name = "reservationId") Long reservationId){
        reservationService.deleteReservation(reservationId);
        return "redirect:/reservationList";
    }

    @GetMapping(path = "/updateReservation/{reservationId}")
    public String updateReservation(Model model, @PathVariable(name = "reservationId") Long reservationId){
        Optional<Reservation> reservationOptional = reservationService.findReservation(reservationId);
        if(reservationOptional.isPresent()){
            Reservation reservation = reservationOptional.get();
            ReservationDTO reservationDTO = new ReservationDTO(reservation.getFirstName(), reservation.getLastName(), reservation.getFilmShow().getFilmShowId(), reservationId);
            model.addAttribute("reservationDTO", reservationDTO); // to "reservation" odnosi się do th:object="${reservation}" w pliku updateReservation.html

            List<FilmShow> filmShows = new ArrayList<>();
            filmShows.addAll(filmShowService.getAllFilmShow());
            model.addAttribute("filmShows", filmShows);

            return "updateReservation";
        }
        return "redirect:/";
    }

    @PostMapping(path = "/updateReservation")
    public String updateReservation(ReservationDTO reservationDTO){

        Optional<FilmShow> filmShowOptional = filmShowService.findFilmShow(reservationDTO.getFilmShowId());

        Optional<Reservation> editedReservation = reservationService.findReservation(reservationDTO.getReservationId());
        Reservation reservation = editedReservation.get();

        reservation.setFilmShow(filmShowOptional.get());
        reservation.setFirstName(reservationDTO.getFirstName());
        reservation.setLastName(reservationDTO.getLastName());

        reservationService.updateReservation(reservation);
        return "redirect:/reservationList/";
    }
}
