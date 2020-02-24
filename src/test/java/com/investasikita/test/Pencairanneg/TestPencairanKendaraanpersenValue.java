/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.investasikita.test.Pencairanneg;

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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author ali
 */
public class TestPencairanKendaraanpersenValue {

    private WebDriver driver;

    @Test
    public void testpencairan() throws InterruptedException, FileNotFoundException, IOException {

        System.out.println("-----------------------------TEST ROBO KENDARAAN DETAIL NABUNG!!!!!!!!!!!-----------------------------");
        driver.get(readStream().getProperty("url"));
        //fix error cant scroll into view 
        WebDriverWait wait = new WebDriverWait(driver, 3);
        JavascriptExecutor js = ((JavascriptExecutor) driver);

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

        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Keluar'])[1]/following::img[3]")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//button[@id='userMenu']/img")).click();
        Thread.sleep(3000);
        //handled cant scroll into view
        driver.findElement(By.xpath("//button[@id='userMenu']/img")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//a[contains(text(),'Tabungan Saya')])[2]")).click();
        Thread.sleep(3000);

        //click kendaraan
        driver.findElement(By.xpath("//a[contains(text(),'Jangan Dihapus (kendaraan)')]")).click();
        Thread.sleep(3000);

        //SELECT PENCAIRAN
        driver.findElement(By.xpath("//button[2]")).click();
        Thread.sleep(3000);

        //select value in text box
        WebElement textbox = driver.findElement(By.id("amount"));
        textbox.clear();
        textbox.sendKeys("51");
        Thread.sleep(2000);

        //get value first
        WebElement tabungan1 = driver.findElement(By.xpath("//div[2]/div/div/span"));
        System.out.println(tabungan1.getText());

        //get value second
        WebElement tabungan2 = driver.findElement(By.xpath("//div[3]/div[2]/div/div/span"));
        System.out.println(tabungan2.getText());

        //get value third 
        WebElement tabungan3 = driver.findElement(By.xpath("//div[4]/div[2]/div/div/span"));
        System.out.println(tabungan3.getText());

        //get value TOTAL tabungan saat ini
        WebElement saved = driver.findElement(By.xpath("//p[2]"));
        System.out.println(saved.getText());

        //get value fee
        WebElement fee = driver.findElement(By.xpath("//form/section/div/div/p"));
        System.out.println(fee.getText());

        //ganti (,) to (.) 
        //tabungan 1
        String val1 = String.valueOf(tabungan1.getText());
        String val2 = val1.replaceAll("Rp. ", "").replaceAll("\\.", "").replaceAll("\\,00", "");
        double a = Double.valueOf(val2);
        System.out.println("source1 = " + a);

        //tabungan 2
        String val3 = String.valueOf(tabungan2.getText());
        String val4 = val3.replaceAll("Rp. ", "").replaceAll("\\.", "").replaceAll("\\,00", "");
        double b = Double.valueOf(val4);
        System.out.println("source2  = " + val4);

        //tabungan 3
        String val5 = String.valueOf(tabungan3.getText());
        String val6 = val5.replaceAll("Rp. ", "").replaceAll("\\.", "").replaceAll("\\,00", "");
        double c = Double.valueOf(val6);
        System.out.println("source3 = " + c);

        String val7 = String.valueOf(saved.getText());
        String val8 = val7.replaceAll("Total Estimasi Pencairan: Rp. ", "").replaceAll("\\.", "").replaceAll(",00", "");
        double d = Double.valueOf(val8);
        System.out.println("saved = " + d);

        String val9 = String.valueOf(fee.getText());
        String val10 = val9.replaceAll("Total Fee: Rp. ", "").replaceAll("\\.", "").replaceAll(",00", "");
        double e = Double.valueOf(val10);
        System.out.println("fee = " + e);

        double hasil = (a + b + c + e);
        System.out.println(hasil);

        double out = Math.round(hasil * 100.0) / 100.0;
        System.out.println("hasil: " + Math.round(hasil * 100.0) / 100.0);

        if (out == d) {
            System.out.println("true");
            System.out.println(out);
        } else {
            System.out.println("false");
            double selisih = out - d;
            System.out.println("selisih = "+ selisih);
            driver.findElement(By.xpath("//bahaya hitungan tidak sesuai!!'")).click();
        }

        System.out.println("-----------------------------end of TEST ROBO KENDARAAN DETAIL NABUNG!!!!!!!!!!!-----------------------------");
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
