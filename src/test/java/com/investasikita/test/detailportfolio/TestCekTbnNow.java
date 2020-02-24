/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.investasikita.test.detailportfolio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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
public class TestCekTbnNow {

    private WebDriver driver;

    @Test
    public void testvalue() throws InterruptedException, FileNotFoundException, IOException {
        System.out.println("-----------------------------TEST PORTFOLIO Detail tabungan saat ini !!!!!!!!!!!-----------------------------");

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
        driver.findElement(By.linkText("Avrist IDX30")).click();
        Thread.sleep(3000);

        //get value TOTAL tabungan saat ini
        WebElement tabungan1 = driver.findElement(By.xpath("//span[2]"));
        System.out.println(tabungan1.getText());

        //ganti (,) to (.) 
        //tabungan saat ini
        String val1 = String.valueOf(tabungan1.getText());
        String val2 = val1.replaceAll("Rp.", "").replaceAll("\\.", "").replaceAll("\\,00", "");
        System.out.println("val 2 = " + val2);
        double a = Double.valueOf(val2);
        System.out.println("tabungan saat ini = " + a);

        //get value investemnt unit
        WebElement unit = driver.findElement(By.xpath("//p[6]/b"));
        System.out.println(unit.getText());

        String val3 = String.valueOf(unit.getText());
        String val4 = val3.replaceAll("\\.", "").replaceAll("\\,", ".");
        System.out.println("val 2 = " + val4);
        double b = Double.valueOf(val4);
        System.out.println(" Unit = " + b);

        //get value NAB akhir 
        WebElement NAB = driver.findElement(By.xpath("//div[@id='root']/div/section[2]/p[2]/b"));
        System.out.println(NAB.getText());

        //Nab terakhir 
        String val5 = String.valueOf(NAB.getText());
        String val6 = val5.replaceAll("\\.", "").replaceAll("\\,", ".");
        double c = Double.valueOf(val6);
        System.out.println("NAB = " + c);

        double aum1 = b * c;
        System.out.println(aum1);

        int decPlaces1 = 0;
        BigDecimal potong = new BigDecimal(aum1);
        System.out.println("Value 1 : " + val1);
        // ROUND_DOWN
        potong = potong.setScale(decPlaces1, BigDecimal.ROUND_DOWN);
        
        double str1 = potong.doubleValue(); 
        System.out.println("Result = " + str1);

        if (str1 == a) {
            System.out.println("true");
            System.out.println(a);
        } else {
            System.out.println("false");
            double selisih = aum1 - a;
            System.out.println("selisih = " + selisih);
            driver.findElement(By.xpath("//bahaya hitungan tidak sesuai!!'")).click();
        }
        System.out.println("-----------------------------end of TEST PORTFOLIO Detail tabungan saat ini!!!!!!!!!!!-----------------------------");

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
