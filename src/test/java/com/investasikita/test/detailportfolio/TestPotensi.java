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
public class TestPotensi {

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

        //get value potensi 
        WebElement potensi = driver.findElement(By.xpath("//div[3]/div/div/span[2]"));
        System.out.println(potensi.getText());

        //ganti (,) to (.) 
        //tabungan potensi
        String val1 = String.valueOf(potensi.getText());
        String val2 = val1.replaceAll("Rp.", "").replaceAll("\\.", "").replaceAll("\\,00", "");
        System.out.println("val 2 = " + val2);
        double a = Double.valueOf(val2);
        System.out.println("potensi = " + a);

        //get value TOTAL tabungan saat ini
        WebElement tabungan1 = driver.findElement(By.xpath("//span[2]"));
        System.out.println(tabungan1.getText());

        //ganti (,) to (.) 
        //tabungan saat ini
        String val3 = String.valueOf(tabungan1.getText());
        String val4 = val3.replaceAll("Rp.", "").replaceAll("\\.", "").replaceAll("\\,00", "");
        System.out.println("val 2 = " + val4);
        double b = Double.valueOf(val4);
        System.out.println("tabungan saat ini = " + b);

        //get value TOTAL tabungan awal (invtmen value)
        WebElement awal = driver.findElement(By.xpath("//p[4]/b"));
        System.out.println(awal.getText());

        //ganti (,) to (.) 
        //tabungan awal (invtmen value) 
        String val5 = String.valueOf(awal.getText());
        String val6 = val5.replaceAll("Rp.", "").replaceAll("\\.", "").replaceAll("\\,00", "");
        System.out.println("val 2 = " + val6);
        double c = Double.valueOf(val6);
        System.out.println("tabungan awal = " + c);

        double aum1 = b - c;
        System.out.println("potensi from cal= " + aum1);

        //get value Potensi
        WebElement potensif = driver.findElement(By.xpath("//div[@id='root']/div/section/div/div[3]/div/div/span[2]"));
        System.out.println(potensif.getText());

        //Potensi to double
        String val7 = String.valueOf(potensif.getText());
        String val8 = val7.replaceAll("Rp.", "").replaceAll("\\.", "").replaceAll("\\,00", "");
        System.out.println("val 8 = " + val8);
        double e = Double.valueOf(val8);
        System.out.println(" Potensi = " + e);      
        
        if ( aum1 == e) {
            System.out.println("true");
            System.out.println(e);
        } else {
            System.out.println("false");
            double selisih = aum1 - e;
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
