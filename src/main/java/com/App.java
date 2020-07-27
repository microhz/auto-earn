package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : jihai
 * @date : 2020/7/27
 * @description :
 */
@Controller
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        new SpringApplication(App.class).run(args);
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("value", "the is my page");
        return "/pages/home";
    }
}
