package com.nvs.store.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @Autowired
    public HelloController() {
    }
//    @GetMapping("/hello")
//    public String hello(@RequestParam(name = "name", required = false, defaultValue = "Vasile") String name, Model model) {
//        model.addAttribute("name", name);
//
//        model.addAttribute("users",userDAO.allUsers());
//        return "hello";
//    }
}
