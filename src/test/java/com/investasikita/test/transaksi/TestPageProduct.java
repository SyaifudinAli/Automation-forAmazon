/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.investasikita.test.transaksi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author ali
 */
public class TestPageProduct {

    private WebDriver driver;

    @Test
    public void testpageproduct() throws InterruptedException, FileNotFoundException, IOException {
        System.out.println("-----------------------------TEST PAGE PRODUCT!!!!!!!!!!!-----------------------------");
        driver.get(readStream().getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Thread.sleep(15000);
        driver.findElement(By.xpath("//a[contains(text(),'Login')]")).click();
        Thread.sleep(5000);

        driver.findElement(By.id("login-email")).sendKeys(readStream().getProperty("login.email"));
        driver.findElement(By.id("login-password")).sendKeys(readStream().getProperty("login.password"));
        driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//li[@id='path-product']/a")).click();
        Thread.sleep(3000);

        driver.findElement(By.linkText("Semua Produk")).click();
        Thread.sleep(5000);
        
        try {
            long lastHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

            while (true) {
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
                Thread.sleep(2000);

                long newHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
                if (newHeight == lastHeight) {
                    break;
                }
                lastHeight = newHeight;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread.sleep(3000);

        boolean hasNextPage = true;
        try {
            while (hasNextPage) {
                List<WebElement> enabled_next_page_btn = driver.findElements(By.xpath("//a[contains(text(),'Next')]"));
                List<WebElement> disabled_next_page_btn = driver.findElements(By.xpath("//div[@id='product-content']/div/div[2]/section/div/div[11]/ul/li[12]"));
                if (enabled_next_page_btn.size() > 0) {
                    enabled_next_page_btn.get(0).click();
                    hasNextPage = true;
                } else if (disabled_next_page_btn.size() > 0) {
                    System.out.println("No more Pages Available");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("No more Pages Available");
        }

       Thread.sleep(4000);

        System.out.println("-----------------------------end of TEST PAGE PRODUCT!!!!!!!!!!!-----------------------------");

    }
    public Properties readStream() {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/application-test.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }

    @BeforeTest
    public void beforeTest() {
        System.setProperty("webdriver.gecko.driver", readStream().getProperty("driver.gecko"));
        driver = new FirefoxDriver();
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }    
}
