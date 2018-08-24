package pl.home.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.home.demo.model.Cinema;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
}
