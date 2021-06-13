package com.example.controller;


import com.example.dao.UserDao;
import com.example.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/user")
// Domyślny scope Singleton
@SessionScope // - bean tworzony raz na sesje użytkownika
//@RequestScope
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

    @GetMapping("/main/{id}")
    public String getSpecificUser(@PathVariable int id, Model model) {
        model.addAttribute("user", userDao.getUserById(id));
        return "index";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam int id, Model model) {
        userDao.deleteUser(id);
        model.addAttribute("users", userDao.getUsers());
        return "index";
    }

    @PostMapping("/edit")
    public String editUser(@RequestParam int id, Model model) {
        model.addAttribute("user", userDao.getUserById(id));
        return "post";
    }

    @PostMapping("/save")
    public String saveUser(User user) {
        userDao.editUser(user);
        return "redirect:main";
    }

    @GetMapping("/addNew")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "post";
    }

}
