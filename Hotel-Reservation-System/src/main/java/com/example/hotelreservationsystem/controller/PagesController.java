package com.example.hotelreservationsystem.controller;

import com.example.hotelreservationsystem.model.Reservations;
import com.example.hotelreservationsystem.model.User;
import com.example.hotelreservationsystem.service.ReservationsService;
import com.example.hotelreservationsystem.service.UserService;
import org.hibernate.mapping.Set;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

@Controller
public class PagesController {
final UserService userService ;
final ReservationsService reservationsService ;

    public PagesController(UserService userService, ReservationsService reservationsService) {
        this.userService = userService;
        this.reservationsService = reservationsService;
    }
    @GetMapping("/")
    public String index(Model model){
return "index" ;

    }
    @GetMapping("/reservations")
    public String reservations(Model model, HttpSession session) {
        UserDetails principal = (UserDetails)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
        User user = userService.getUserByUsername(name);
        if (user != null){
            // This should always be the case
            session.setAttribute("user" , user);

            // Empty reservation object in case the user creates a new reservation
            Reservations reservations = new Reservations();
            model.addAttribute("reservation" , reservations);
            return "reservation" ;
        }
        return "index" ;
    }
    @PostMapping("/reservations-submit")
    public String reservationsSubmit(@ModelAttribute Reservations reservations , Model model ,
                                     @SessionAttribute("user") User user){
assert user !=null ;
reservations.setUser(user);
reservationsService.create(reservations);
        Set<Reservations> userReservations = user.getReservations();
        userReservations.add(reservations);
        user.setReservations(userReservations);
        userService.update(user.getId(),user);
        return "redirect:/reservations";
    }
    @GetMapping("/get/all-reservations")
    public String getAllReservations()
    {
        return "reservationsList";
    }
}
