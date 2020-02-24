/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.investasikita.test.robonabung;

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
public class Test_OtherCal {

    private WebDriver driver;

    @Test
    public void testOther() throws InterruptedException, FileNotFoundException, IOException {

        System.out.println("-----------------------------TEST ROBO OTHER DETAIL NABUNG!!!!!!!!!!!-----------------------------");
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
        driver.findElement(By.xpath("(//a[contains(text(),'Tabungan Saya')])[2]")).click();
        Thread.sleep(3000);

        //click other
        driver.findElement(By.xpath("//a[contains(text(),'Jangan Dihapus Umroh')]")).click();
        Thread.sleep(3000);

        //get value tabungan perbulan diluar
        WebElement tabungan1 = driver.findElement(By.xpath("//section[2]/div/div/p"));
        System.out.println(tabungan1.getText());

        //tabungan 1
        String val1 = String.valueOf(tabungan1.getText());
        String val2 = val1.replaceAll("Tabungan 2 tahun sebesar Rp. ", "").replaceAll("\\.", "").replaceAll("\\,", "").replaceAll("/bulan", "");
        System.out.println("val2 = " + val2);
        double a = Double.valueOf(val2);
        System.out.println("a = " + a);

///////////////////////////////////////////////////////////////////////////////
        //SELECT NABUNG
        driver.findElement(By.xpath("//div[2]/div/button")).click();
        Thread.sleep(3000);
        
        //get value tabungan perbulan didalam
        WebElement dalam = driver.findElement(By.id("mutualFund"));
        String nilaiTab = dalam.getAttribute("value");
        System.out.println("nilaitab = " + nilaiTab);

        //nilai tab1
        String val4 = nilaiTab.replaceAll("\\.", "").replaceAll("\\,", "");
        System.out.println("val4 = " + val4);
        double b = Double.valueOf(val4);
        System.out.println("b = " + b);

////////////////////////////////////////////////////////////////////////////////        
        //get value reksadana 1
        WebElement reksa1 = driver.findElement(By.xpath("//form/section/div[2]/div/span"));
        System.out.println(reksa1.getText());

        //reksadana 1
        String val5 = String.valueOf(reksa1.getText());
        String val6 = val5.replaceAll("Rp. ", "").replaceAll("\\.", "").replaceAll("\\,00", "");
        System.out.println("val6 = " + val6);
        double c = Double.valueOf(val6);
        System.out.println("c  = " + c);

        //get value reksadana 2
        WebElement reksa2 = driver.findElement(By.xpath("//div[3]/div/span"));
        System.out.println(reksa2.getText());

        //reksadana 2
        String val7 = String.valueOf(reksa2.getText());
        String val8 = val7.replaceAll("Rp. ", "").replaceAll("\\.", "").replaceAll("\\,00", "");
        System.out.println("val8 = " + val8);
        double d = Double.valueOf(val8);
        System.out.println("d = " + d);

        //get value reksadana 3
        WebElement reksa3 = driver.findElement(By.xpath("//div[4]/div/span"));
        System.out.println(reksa3.getText());

        //reksadana 3
        String val9 = String.valueOf(reksa3.getText());
        String val10 = val9.replaceAll("Rp. ", "").replaceAll("\\.", "").replaceAll("\\,00", "");
        System.out.println("val10 = " + val10);
        double e = Double.valueOf(val10);
        System.out.println("e = " + e);

        double jum = (c + d + e);
        System.out.println("nilai tabungan = " + jum);
        
        //get value fee
        WebElement fee = driver.findElement(By.xpath("//p[2]"));
        System.out.println(fee.getText());   
        
        String fee1 = String.valueOf(fee.getText());
        String fee2 = fee1.replaceAll("Total Fee: Rp. ", "").replaceAll("\\.", "");
        System.out.println("fee = " + fee2);
        double f = Double.valueOf(fee2);
        System.out.println("fee  = " + f);
        
        //get value fee
        WebElement total = driver.findElement(By.xpath("//p[3]"));
        System.out.println(total.getText());   
        
        String total1 = String.valueOf(total.getText());
        String total2 = total1.replaceAll("Total : Rp", "").replaceAll("\\.", "");
        System.out.println("total = " + total2);
        double g = Double.valueOf(total2);
        System.out.println("total  = " + g);

        double totalall = jum + f;
        

        if (g == totalall) {
            System.out.println("true");
        } else {
            System.out.println("false");
            double selisih = (g-totalall);
            System.out.println (g + "-" + totalall + "=" + selisih );
            
            System.out.println("tidak sama, selisih sama dengan = " + selisih);
            driver.findElement(By.xpath("// hitungan tidak sesuai!!'")).click();
        }

        Thread.sleep(2000);


        System.out.println("-----------------------------end of TEST ROBO OTHER DETAIL NABUNG!!!!!!!!!!!-----------------------------");
        Thread.interrupted();
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
