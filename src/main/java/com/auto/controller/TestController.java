package com.auto.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

/**
 * @author : jihai
 * @date : 2020/12/24
 * @description :
 */
@RestController
@RequestMapping("test")
@SpringBootApplication
public class TestController {

    /*@RequestMapping(value = "a", method = RequestMethod.POST)
    public Object request(@RequestParam("userId") Long userId) {
        System.out.println(userId);
        return true;
    }*/

    static class DTO {
        private Long userId;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }


    @RequestMapping(value = "b", method = RequestMethod.POST)
    public Object request(@RequestParam Long userId) {
        System.out.println(userId + ":");
        return userId;
    }

    public static void main(String[] args) {
        new SpringApplication(TestController.class).run(args);
    }
}
