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
public class TestUnitAmmount {

    private WebDriver driver;

    @Test
    public void testvalue() throws InterruptedException, FileNotFoundException, IOException {
        System.out.println("-----------------------------TEST PORTFOLIO Detail Unit Ammount  !!!!!!!!!!!-----------------------------");

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
//        driver.findElement(By.linkText("SUCORINVEST MONEY MARKET FUND")).click();
//        Thread.sleep(3000);
        driver.findElement(By.linkText("Danareksa Melati Pendapatan Utama")).click();
        Thread.sleep(3000);

        //get value TOTAL tabungan awal (invtmen value)
        WebElement tabungan1 = driver.findElement(By.xpath("//p[4]/b"));
        System.out.println(tabungan1.getText());

        //ganti (,) to (.) 
        //tabungan awal (invtmen value) 
        String val1 = String.valueOf(tabungan1.getText());
        String val2 = val1.replaceAll("Rp.", "").replaceAll("\\.", "").replaceAll("\\,00", "");
        System.out.println("val 2 = " + val2);
        double a = Double.valueOf(val2);
        System.out.println("tabungan awal = " + a);

        //get value nab rata2
        WebElement unit = driver.findElement(By.xpath("//p[8]/b"));
        System.out.println(unit.getText());

        String val3 = String.valueOf(unit.getText());
        String val4 = val3.replaceAll("\\.", "").replaceAll("\\,", ".");
        System.out.println("val 2 = " + val4);
        double b = Double.valueOf(val4);
        System.out.println(" Unit = " + b);

        //hitung jumlah unit
        double AVnab = a / b;
        System.out.println("NAB rata-rata = " + AVnab);

        double bulat = AVnab * 100;
        bulat = bulat * 100;
        bulat = Math.floor(bulat);
        bulat = bulat / 100;

        System.out.println("bulat = " + bulat);

////////////////////////////////////////////////////////////////////////////
        //get value jumlah unit front end 
        WebElement NAB = driver.findElement(By.xpath("//p[6]/b"));
        System.out.println(NAB.getText());

        //nab akhir 
        String val5 = String.valueOf(NAB.getText());
        String val6 = val5.replaceAll("\\.", "").replaceAll("\\,", ".");
        double c = Double.valueOf(val6);
        System.out.println("NAB = " + c);

//        //hitung return (rtrn terakhir - rtrn rata2) / rtrn rata2 
//        double growth = (c - AVnab) / AVnab;
//        System.out.println(growth);
//
//        double bulat = growth * 100;
//
//        bulat = bulat * 100;
//        bulat = Math.floor(bulat);
//        bulat = bulat / 100;
//
//        System.out.println(bulat);
//
//        //get value rtrn akhir 
//        WebElement rtrn = driver.findElement(By.xpath("//div[2]/div/div/span[2]"));
//        System.out.println(rtrn.getText());
//
//        //Nab terakhir 
//        String val9 = String.valueOf(rtrn.getText());
//        String val10 = val9.replaceAll("\\,", ".").replaceAll("\\%", "");
//        System.out.println(val10);
//        double g = Double.valueOf(val10);
//        System.out.println("rtrn from web = " + g);
        if (AVnab == c) {
            System.out.println("true");
            System.out.println(a);
        } else {
            System.out.println("false");
            double selisih = AVnab - c;
            System.out.println("selisih = " + selisih);
            driver.findElement(By.xpath("//bahaya hitungan tidak sesuai!!'")).click();
        }
        System.out.println("-----------------------------end of TEST PORTFOLIO Detail Unit Ammount !!!!!!!!!!!-----------------------------");

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
