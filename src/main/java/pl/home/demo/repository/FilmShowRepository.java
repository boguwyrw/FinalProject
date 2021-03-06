package pl.home.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.home.demo.model.FilmShow;

import java.util.List;

@Repository
public interface FilmShowRepository extends JpaRepository<FilmShow, Long> {

}
