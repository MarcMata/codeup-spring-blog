package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Category;
import com.codeup.codeupspringblog.models.Comment;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.repositories.CategoryRepository;
import com.codeup.codeupspringblog.repositories.CommentRepository;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class PostController {
    private PostRepository postsDao;
    private UserRepository usersDao;
    private CommentRepository commentsDao;
    private CategoryRepository categoriesDao;


    public PostController(PostRepository postsDao, UserRepository usersDao, CommentRepository commentsDao, CategoryRepository categoriesDao) {
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.commentsDao = commentsDao;
        this.categoriesDao = categoriesDao;
    }

    @GetMapping("/posts")
    public String allPosts(Model model) {
        System.out.println(model.addAttribute("posts",postsDao.findAll()));
        model.addAttribute("posts", postsDao.findAll());;
        return "posts/index";
    }

    @GetMapping("/posts/{id}/view")
    public String viewIndividualPost(@PathVariable long id, Model model) {
        Post post = postsDao.findById(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@PathVariable long id, @RequestParam(name = "title") String title, @RequestParam(name = "body") String body) {
        Post post = postsDao.findById(id);

        if (post != null) {
            post.setTitle(title);
            post.setBody(body);
            postsDao.save(post);
        }

        return "redirect:/posts";
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
    public String createPost(@RequestParam(name="title") String title, @RequestParam(name="body") String body, @RequestParam(name="categories") String categories) {
        Post post = new Post(title, body);
        Set<Category> categorySet = makeCategorySet(categories);
        if (categorySet.size()>0) {
            List<Category> categoriesToAdd = new ArrayList<>();
            for (Category category : categorySet) {
                Category categoryFromDb = categoriesDao.findCategoriesByName(category.getName());
                if (categoryFromDb == null) {
                    categoriesToAdd.add(category);
                } else {
                    categoriesToAdd.add(categoryFromDb);
                }
            }
            categorySet.clear();
            //repopulates with new list
            categorySet.addAll(categoriesToAdd);

            post.setCategories(categorySet);
        }
        postsDao.save(post);
        return "redirect:/posts";
    }

    //helper method to take a comma separated list and return a set of objects
    public Set<Category> makeCategorySet(String categoriesCsl){
        //create an empty list of tag objects
        Set<Category> categoryObjects = new HashSet<>();
        if (categoriesCsl.equals("")){
            return categoryObjects;
        }
        //creates an Array of strings to loop over it
        for (String category : categoriesCsl.split(",")){
            //for each tag string, get the tag object from the database
            Category categoryObject = new Category(category.trim());
            //add the tag object to the list
            categoryObjects.add(categoryObject);
        }
        return categoryObjects;
    }

    @PostMapping("/posts/comment")
    public String submitComment(@RequestParam(name="content")String content, @RequestParam(name="postId") long postId, Model model){
        Post post = postsDao.findById(postId);
        Comment comment = new Comment(content, post);

        commentsDao.save(comment);
        model.addAttribute("id", postId);
        return "redirect:/posts/" + postId + "/view";
    }
}

