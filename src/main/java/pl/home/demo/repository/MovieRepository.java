package pl.home.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.home.demo.model.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findAllByTitleContaining(String title);
    List<Movie> findAllByOrderByTitleAsc();
    List<Movie> findAllByOrderByTitleDesc();
}
