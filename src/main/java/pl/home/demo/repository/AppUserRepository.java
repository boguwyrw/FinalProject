package pl.home.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.home.demo.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

}
