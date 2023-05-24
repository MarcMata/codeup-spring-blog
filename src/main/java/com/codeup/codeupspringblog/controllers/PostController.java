package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String allPosts() {
        return "posts index page";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String viewIndividualPost(@PathVariable int id) {
        return "view an individual post";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createForm() {
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String submitForm() {
        return "create a new post";
    }

}

