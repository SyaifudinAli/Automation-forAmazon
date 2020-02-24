/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.investasikita.test.portfolio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
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
public class TestImbalHasil {
    private WebDriver driver;

    @Test
    public void testvalue() throws InterruptedException, FileNotFoundException, IOException {
        System.out.println("-----------------------------TEST PORTFOLIO VALUE imbal HASIL !!!!!!!!!!!-----------------------------");

        driver.get(readStream().getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Thread.sleep(15000);
        driver.findElement(By.xpath("//a[contains(text(),'Login')]")).click();
        Thread.sleep(3000);

        driver.findElement(By.id("login-email")).sendKeys(readStream().getProperty("login.email"));
        driver.findElement(By.id("login-password")).sendKeys(readStream().getProperty("login.password"));
        Thread.interrupted();
        driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//button[@id='userMenu']/img")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//a[contains(@href, '#/portfolio')])[2]")).click();
        Thread.sleep(3000);

        //get value TOTAL tabungan saat ini
        WebElement tabungan1 = driver.findElement(By.xpath("//section/div/div/div/p[2]"));
        System.out.println(tabungan1.getText());

        //get value TOTAL tabungan AWAL
        WebElement saved = driver.findElement(By.xpath("//div[2]/div/p[2]"));
        System.out.println(saved.getText());

        //ganti (,) to (.) 
        String val1 = String.valueOf(tabungan1.getText());
        String val2 = val1.replaceAll("Rp.", "").replaceAll("\\.", "").replaceAll("\\,00", "");
        System.out.println("val 2 = " + val2);
        double a = Double.valueOf(val2);
        System.out.println("tabungan now = " + a);
 
        String val3 = String.valueOf(saved.getText());
        String val4 = val3.replaceAll("Rp.", "").replaceAll("\\.", "").replaceAll("\\,00", "");
        System.out.println("val 2 = " + val4);
        double b = Double.valueOf(val4);
        System.out.println("tabungan first = " + b);

        double hasil = (a - b );
        System.out.println(hasil);
        
        double out = Math.round(hasil * 100.0) / 100.0;
        System.out.println("hasil: " + Math.round(hasil * 100.0) / 100.0);

        System.out.println("-----------------------------------------------------");
        

        //get value imbal 
        WebElement imbal = driver.findElement(By.xpath("//div[2]/p[2]"));
        System.out.println(imbal.getText());
        

        //imbal 
        String val5 = String.valueOf(imbal.getText());
        String val6 = val5.replaceAll("Rp.", "").replaceAll("\\.", "").replaceAll("\\,00", "");
        double c = Double.valueOf(val6);
        System.out.println("imbal = " + c);
        
        if (c == hasil) {
            System.out.println("true");
            System.out.println(hasil);
        } else {
            System.out.println("false");
            driver.findElement(By.xpath("//bahaya hitungan tidak sesuai!!'")).click();
        }
        System.out.println("-----------------------------end of TEST PORTFOLIO VALUE imbal HASIL!!!!!!!!!!!-----------------------------");

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
//        driver.quit();
    }           
}
