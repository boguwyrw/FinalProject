package pl.home.demo.controller;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.home.demo.model.AppUser;
import pl.home.demo.model.AppUserDTO;
import pl.home.demo.model.Reservation;
import pl.home.demo.model.UpdateAppUserDTO;
import pl.home.demo.service.AppUserService;
import pl.home.demo.service.ReservationService;

import java.security.Principal;
import java.util.Optional;

@Controller
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping(path = "/addAppUser")
    public String addAppUser(Model model){
        AppUserDTO appUserDTO = new AppUserDTO();
        model.addAttribute("appUserDTO", appUserDTO);
        return "addAppUser";
    }

    @PostMapping(path = "/addAppUser")
    public String addAppUser(Model model, AppUserDTO appUserDTO){
        appUserService.addAppUser(appUserDTO);
        return "redirect:/";
    }

    @GetMapping(path = "/login")
    public String login(){
        return "login";
    }

    @GetMapping(path = "/updateAppUser")
    public String updateAppUser(Model model, Principal principal){

        Optional<AppUser> appUserOptional = appUserService.findAppUser(principal.getName());
        if(appUserOptional.isPresent()){
            AppUser appUser = appUserOptional.get();
            UpdateAppUserDTO appUserDTO = new UpdateAppUserDTO(appUser.getUserId(),
                    appUser.getFirstName(),
                    appUser.getLastName(), null, null);
            model.addAttribute("edited_user", appUserDTO);
            return "updateAppUser";
        }
        return "redirect:/";
    }

    @PostMapping(path = "/updateAppUser")
    public String updateAppUser(UpdateAppUserDTO appUserDTO){
        appUserService.updateUser(appUserDTO);

        return "redirect:/";
    }
}
