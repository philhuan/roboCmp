package com.hjwblog.robo_cmp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

    @GetMapping("/hello")
    private String helloWorld(Model model){
        model.addAttribute("hello", "hhh");
        return "hello";
    }
}
