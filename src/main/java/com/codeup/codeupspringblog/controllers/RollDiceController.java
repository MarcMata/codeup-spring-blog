package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Dice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String rollDice() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{guess}")
    public String rollDiceGuess(@PathVariable int guess, Model model) {
        int random = (int) Math.floor(Math.random() * 6 + 1);
        System.out.println(random);
        if (guess == random) {
            model.addAttribute("guess", guess);
            model.addAttribute("random", random);
            model.addAttribute("message", "You guessed correctly!");
            return "roll-dice";
        } else {
            model.addAttribute("guess", guess);
            model.addAttribute("random", random);
            model.addAttribute("message", "You guessed incorrectly!");
            return "roll-dice";
        }
    }
}
