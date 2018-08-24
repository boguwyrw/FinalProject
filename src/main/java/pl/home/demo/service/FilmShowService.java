package pl.home.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.home.demo.model.FilmShow;
import pl.home.demo.repository.FilmShowRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FilmShowService {

    @Autowired
    private FilmShowRepository filmShowRepository;

    public void addFilmShow(FilmShow filmShow){
        filmShowRepository.save(filmShow);
    }

    public List<FilmShow> getAllFilmShow(){
        return filmShowRepository.findAll();
    }

    public void deleteFilmShow(Long filmShowId){
        filmShowRepository.deleteById(filmShowId);
    }

    public Optional<FilmShow> findFilmShow(Long filmShowId) {
        return filmShowRepository.findById(filmShowId);
    }

    public void updateFilmShow(FilmShow filmShow) {
        filmShowRepository.save(filmShow);
    }
}
