package pl.home.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.home.demo.model.AppUser;
import pl.home.demo.model.AppUserDTO;
import pl.home.demo.repository.AppUserRepository;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void addAppUser(AppUserDTO appUserDTO) {
        // sprawdzenie czy uzytkownik wpisal w pole haslo i powtorz haslo to samo
        if (appUserDTO.getConfirm_password().equals(appUserDTO.getPassword())) {
            AppUser appUserCreated = new AppUser();
            appUserCreated.setUsername(appUserDTO.getUsername());
            String encrypted = bCryptPasswordEncoder.encode(appUserDTO.getPassword());
            appUserCreated.setPassword(encrypted);

            appUserRepository.save(appUserCreated);
        }
    }
}
