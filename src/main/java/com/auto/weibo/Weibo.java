package com.auto.weibo;

import com.auto.common.ChromeSupport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;

/**
 * @author : jihai
 * @date : 2020/11/4
 * @description :
 */
public class Weibo extends ChromeSupport {

    public void openAndCancel() {
        try {
            init();
            openPage("https://weibo.com/p/1005052273156342/myfollow");
            waitLongLoading();

            WebElement element = webDriver.findElement(By.xpath("//a[text()='批量管理']"));
            element.click();

            WebElement el = null;
            while ((el = webDriver.findElement(By.xpath("//div[@class='member_wrap clearfix ']"))) != null) {
                el.click();
                shortWait();
            }

            WebElement cancel = webDriver.findElement(By.xpath("//[text()='取消关注']"));
            cancel.click();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
