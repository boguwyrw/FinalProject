package pl.home.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.home.demo.model.AppUser;
import pl.home.demo.model.Movie;
import pl.home.demo.model.Reservation;
import pl.home.demo.repository.ReservationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;


    public Reservation addReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }

    public List<Reservation> getAllMyReservations(AppUser appUser){
        return reservationRepository.findAllByAppUser(appUser);
    }

    public void deleteReservation(Long reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    public Optional<Reservation> findReservation(Long reservationId) {
        return reservationRepository.findById(reservationId);
    }

    public void updateReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }
}
