package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    @GetMapping("/posts")
    public String allPosts(Model model) {
        List<Post> allPosts = new ArrayList<>();
        Post post1 = new Post("Test title1", "lorem ipsum dolor sit amet");
        Post post2 = new Post("Test title2", "lorem ipsum dolor sit amet");
        System.out.println(post1);
        System.out.println(post2);
        allPosts.add(post1);
        allPosts.add(post2);
        model.addAttribute("posts", allPosts);

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String viewIndividualPost(@PathVariable int id, Model model) {
        Post post = new Post("Test Title", "lorem ipsum dolor sit amet");
        System.out.println(post.getTitle());
        System.out.println(post.getBody());
        model.addAttribute("post", post);
        return "posts/show";
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

