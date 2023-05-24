package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello from Spring!";
    }

    @GetMapping("/hello/{name}")
    @ResponseBody
    public String sayHello(@PathVariable String name) {
        return "Hello " + name + "!";
    }

    @RequestMapping(path = "/hello/{name}", method = RequestMethod.POST)
    @ResponseBody
    public String helloWithPost(@PathVariable String name) {
        return "Hello " + name + "!";
    }


    @GetMapping("/hello/{name1}/and/{name2}")
    @ResponseBody
    public String sayHelloTwo(@PathVariable String name1, @PathVariable String name2) {
        return "Hello " + name1 + " and " + name2;
    }
}

