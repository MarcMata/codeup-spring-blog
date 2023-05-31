package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserRepository usersDao;

    public UserController(UserRepository userDao) {
        this.usersDao = userDao;
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "/register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam (name="username") String username, @RequestParam (name="email") String email, @RequestParam (name="password") String password, @RequestParam (name="confirmPassword") String confirmPassword) {
        //check if username is already taken
        if (!password.equals(confirmPassword)) {
            return "redirect:/register";
        }
        //create new user object
        User user = new User(username, email, password);
        //saves user
        usersDao.save(user);
        //redirects to login page
        return "redirect:/ads";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }
}
