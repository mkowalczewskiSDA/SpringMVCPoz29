package com.example.controller;


import com.example.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/user")
// Domyślny scope Singleton
@SessionScope // - bean tworzony raz na sesje użytkownika
// - jeżeli otworzymy aplikacje w innej przelądarce - zobaczymy w konsoli inicjalizacje
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostConstruct
    public void init() {
        System.out.println("Zainicjalizowano UserController");
    }

    @GetMapping("/main")
    public String mainPage(Model model) {
        // dostarczamy do warstwy widoku atrybut o nazwie users
        model.addAttribute("users", userDao.getUsers());
        return "index";
    }

}
