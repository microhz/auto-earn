package com.auto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * @author : jihai
 * @date : 2020/6/20
 * @description :
 */
@SpringBootApplication
@RestController
public class SpringApp  {

    private int index;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringApp.class);

        Properties properties = new Properties();
        properties.put("system.props.env", "test");
        springApplication.setDefaultProperties(properties);
        springApplication.run(args);
    }

    @PostConstruct
    public void init() {
    }

    @RequestMapping("hi")
    public String hello() {
        try {
            System.out.println(++ index);
            Thread.sleep(1000);
            URL url = new URL("http://localhost:8080/hi");
            url.openConnection();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "hello";
    }

}
