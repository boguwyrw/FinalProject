package pl.home.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.home.demo.model.AppUser;
import pl.home.demo.model.AppUserDTO;
import pl.home.demo.model.UpdateAppUserDTO;
import pl.home.demo.repository.AppUserRepository;

import java.util.Optional;

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

    public Optional<AppUser> findAppUser(Long userId) {
        return appUserRepository.findById(userId);
    }

    public Optional<AppUser> findAppUser(String name) {
        return appUserRepository.findByUsername(name);
    }

    public void updateUser(UpdateAppUserDTO appUserDTO) {
        Optional<AppUser> appUserOptional = findAppUser(appUserDTO.getUserId());
        AppUser appUser = appUserOptional.get();
        appUser.setFirstName(appUserDTO.getFirstname());
        appUser.setLastName(appUserDTO.getLastname());
        if(!appUserDTO.getPassword().isEmpty()) { // sprawdzamy ze haslo jest niepuste
            if(appUserDTO.getPassword().equals(appUserDTO.getConfirm_password())) {
                String encrypted = bCryptPasswordEncoder.encode(appUserDTO.getPassword());
                appUser.setPassword(encrypted);
            }
        }
        appUserRepository.save(appUser);
    }

    public void save(AppUser user) {
        appUserRepository.save(user);
    }
}
