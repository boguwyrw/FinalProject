package pl.home.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.home.demo.model.AppUserDTO;
import pl.home.demo.service.AppUserService;
import pl.home.demo.service.ReservationService;

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
}
