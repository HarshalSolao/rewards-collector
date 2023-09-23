package com.rewards.collector;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.IntStream;

@SpringBootApplication
public class RewardsCollectorApplication {
    public static void main(String[] args) {
        SpringApplication.run(RewardsCollectorApplication.class, args);
        System.setProperty("webdriver.chrome.driver", "C:\\selenium_drivers\\chromedriver_117.exe");
        RewardsUtils rewardsUtils = new RewardsUtils();
        String url =  "https://login.live.com/login.srf?wa=wsignin1.0&rpsnv=16&id=264960&wreply=https%3a%2f%2fwww.bing.com%2fsecure%2fPassport.aspx%3fedge_suppress_profile_switch%3d1%26requrl%3dhttps%253a%252f%252fwww.bing.com%252fsearch%253fq%253dJohny%252bJohny%2526form%253dQBLH%2526sp%253d-1%2526lq%253d0%2526pq%253d%2526sc%253d0-0%2526qs%253dn%2526sk%253d%2526cvid%253dDC2F815F85954780AEE37F5ABCB66A57%2526ghsh%253d0%2526ghacc%253d0%2526ghpl%253d%2526wlexpsignin%253d1%26sig%3d0066CA7D311E65BF3B76D9F9302064FD%26nopa%3d2&wp=MBI_SSL&lc=1033&CSRFToken=22fd63af-2a00-4329-bb0d-ff510ddf4534&aadredir=1&nopa=2";
        try {
            rewardsUtils.getAccounts().forEach((key, value) -> {
                WebDriver webDriver = new ChromeDriver();
                webDriver.manage().window().maximize();
                webDriver.get(url);
                waitFor(1000);

                getWebElement(webDriver,By.xpath("//*[@id=\"i0116\"]")).sendKeys(key);

                getWebElement(webDriver,By.xpath("//*[@id=\"idSIButton9\"]")).click();

                waitFor(1000);

                getWebElement(webDriver,By.xpath("//*[@id=\"i0118\"]")).sendKeys(value);

                getWebElement(webDriver,By.xpath("//*[@id=\"idSIButton9\"]")).click();

                waitFor(2000);

                getWebElement(webDriver,By.xpath("//*[@id=\"KmsiCheckboxField\"]")).click();

                waitFor(1000);

                getWebElement(webDriver,By.xpath("//*[@id=\"idSIButton9\"]")).click();

                waitFor(2000);

                IntStream.range(0,30).forEach(i -> {
                  WebElement webElement = getWebElement(webDriver,By.xpath("//*[@id=\"sb_form_q\"]"));
                  webElement.clear();
                  webElement.sendKeys(rewardsUtils.getFullName());
                  webElement.sendKeys(Keys.ENTER);
                  waitFor(2000);
                });

                webDriver.quit();

            });
            System.out.println(rewardsUtils.getFullName());
        } catch (Exception e) {
            System.out.println("Failed to execute " + e.getMessage());
        }
    }

    public static void waitFor(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static WebElement getWebElement(WebDriver webDriver, By by){
        return webDriver.findElement(by);
    }

}
