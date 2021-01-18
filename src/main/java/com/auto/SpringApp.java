package com.auto;

import com.alibaba.dubbo.config.annotation.Reference;
import com.auto.interview.algorithm.leetcode.utils.Assert;
import com.ggj.item.stock.api.StockOperateAPI;
import com.ggj.item.stock.api.StockQueryAPI;
import com.ggj.item.stock.dto.SkuStockDTO;
import com.ggj.platform.gsf.result.PlainResult;
import com.google.common.collect.Lists;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 * @author : jihai
 * @date : 2020/6/20
 * @description :
 */
@SpringBootApplication
@RestController
public class SpringApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringApp.class);

        Properties properties = new Properties();
        properties.put("system.props.env", "test");
        springApplication.setDefaultProperties(properties);
        springApplication.run(args);
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

    @Reference
    private StockQueryAPI stockQueryAPI;

    @Override
    public void run(String... args) throws Exception {
        PlainResult<List<SkuStockDTO>> listPlainResult = stockQueryAPI.listAllStock(Lists.newArrayList(1L));
        Assert.assertTrue(listPlainResult.isOk());
    }
}
