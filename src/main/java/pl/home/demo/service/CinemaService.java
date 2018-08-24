package pl.home.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.home.demo.model.Cinema;
import pl.home.demo.repository.CinemaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    public void addCinema(Cinema cinema){
        cinemaRepository.save(cinema);
    }

    public List<Cinema> getAllCinemas(){
        return cinemaRepository.findAll();
    }

    public Optional<Cinema> findCinema(Long cinemaId) {
        return cinemaRepository.findById(cinemaId);
    }

    public void updateCinema(Cinema cinema) {
        cinemaRepository.save(cinema);
    }

    public void deleteCinema(Long cinemaId) {
        cinemaRepository.deleteById(cinemaId);
    }
}
