package com.auto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;

/**
 * @author : jihai
 * @date : 2020/6/20
 * @description :
 */
@SpringBootApplication
@RestController
public class SpringApp {

    public static void main(String[] args) {
        new SpringApplication(SpringApp.class).run(args);
    }

    @RequestMapping("hi")
    public String hello() {

        try {
            URL url = new URL("http://localhost:8080/hi");
            url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "hello";
    }

}
