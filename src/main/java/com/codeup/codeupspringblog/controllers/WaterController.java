package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WaterController {
    @GetMapping("/water")
    public String coffeePage() {
        return "water";
    }

    @GetMapping("/water/{water_brand}")
    public String favRoast(@PathVariable String water_brand, Model model) {
        model.addAttribute("water_brand", water_brand);
        return "water";
    }
}
