package pl.home.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.home.demo.model.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);
}
