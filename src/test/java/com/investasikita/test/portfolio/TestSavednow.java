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
public class TestSavednow {
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
        
        Thread.sleep(2000);

        //get value pasar uang
        WebElement pasar = driver.findElement(By.xpath("//p[2]/span"));
        System.out.println(pasar.getText());
        //get value pendapatan tetap         
        WebElement pendapatantetap = driver.findElement(By.xpath("//li[2]/p[2]/span"));
        System.out.println(pendapatantetap.getText());
        //get value campuran
        WebElement campuran = driver.findElement(By.xpath("//li[3]/p[2]/span"));
        System.out.println(campuran.getText());
        //get value index
        WebElement index = driver.findElement(By.xpath("//li[4]/p[2]/span"));
        System.out.println(index.getText());
        //get value saham
        WebElement saham = driver.findElement(By.xpath("//li[5]/p[2]/span"));
        System.out.println(saham.getText());
        
        //get value TOTAL tabungan saat ini
        WebElement tabungan1 = driver.findElement(By.xpath("//section/div/div/div/p[2]"));
        System.out.println(tabungan1.getText());

        //ganti (,) to (.) 
        //PASAR UANG
        String val1 = String.valueOf(pasar.getText());
        String val2 = val1.replaceAll("Rp.", "").replaceAll("\\,", "");
        System.out.println("val 2 = " + val2);
        double a = Double.valueOf(val2);
        System.out.println("pasar = " + a);
        //pedapatan tetap
        String val3 = String.valueOf(pendapatantetap.getText());
        String val4 = val3.replaceAll("Rp.", "").replaceAll("\\,", "");
        System.out.println("val 4 = " + val4);
        double b = Double.valueOf(val4);
        System.out.println("tetap = " + b);
        //campuran
        String val5 = String.valueOf(campuran.getText());
        String val6 = val5.replaceAll("Rp.", "").replaceAll("\\,", "");
        System.out.println("val 6 = " + val6);
        double c = Double.valueOf(val6);
        System.out.println("campuran = " + c);
        //index 
        String val7 = String.valueOf(index.getText());
        String val8 = val7.replaceAll("Rp.", "").replaceAll("\\,", "");
        System.out.println("val 8 = " + val8);
        double d = Double.valueOf(val8);
        System.out.println("index = " + d);
        //saham 
        String val9 = String.valueOf(saham.getText());
        String val10 = val9.replaceAll("Rp.", "").replaceAll("\\,", "");
        System.out.println("val 10 = " + val10);
        double e = Double.valueOf(val10);
        System.out.println("saham = " + e);

        double hasil = (a + b + c + d + e);
        System.out.println(hasil);
        
        double out = Math.round(hasil * 100.0) / 100.0;
        System.out.println("hasil: " + Math.round(hasil * 100.0) / 100.0);

        System.out.println("-----------------------------------------------------");
        
        
        String val11 = String.valueOf(tabungan1.getText());
        String val12 = val11.replaceAll("Rp.", "").replaceAll("\\.", "").replaceAll("\\,00", "");
        System.out.println("val 2 = " + val12);
        double f = Double.valueOf(val12);
        System.out.println("tabungan now = " + f);        
        
        if (f == hasil) {
            System.out.println("true");
            System.out.println(hasil);
        } else {
            System.out.println("false");
            double selisih = f - hasil;
            System.out.println("selisih = " + selisih);
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
