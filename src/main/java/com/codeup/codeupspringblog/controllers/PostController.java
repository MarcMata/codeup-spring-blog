package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private final PostRepository postsDao;

    public PostController(PostRepository postsDao) {
        this.postsDao = postsDao;
    }

    @GetMapping("/posts")
    public String allPosts(Model model) {
        model.addAttribute("posts", postsDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}/view")
    public String viewIndividualPost(@PathVariable long id, Model model) {
        Post post = postsDao.getReferenceById(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPostForm(@PathVariable long id, Model model) {
        Post post = postsDao.getReferenceById(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        postsDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String createForm() {
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@RequestParam(name="title") String title, @RequestParam(name="body") String body) {
        Post post = new Post(title, body);
        postsDao.save(post);
        return "redirect:/posts";
    }
}

