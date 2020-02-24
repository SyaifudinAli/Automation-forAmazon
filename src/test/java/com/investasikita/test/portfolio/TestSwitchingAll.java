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
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author ali
 */
public class TestSwitchingAll {

    private WebDriver driver;

    @Test
    public void testtambahmanual() throws InterruptedException, FileNotFoundException, IOException {

        System.out.println("-----------------------------TEST SWITCHING !!!!!!!!!!!-----------------------------");
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
        driver.get("http://192.168.2.200:8083/#/portfolio-detail/U2FsdGVkX19cwSdnYZyYCShqidAhzPC1Lzdqs0c0TmgMl32");        
        Thread.sleep(3000);
        
        //CLICK SWITCH
        driver.findElement(By.linkText("Alihkan")).click();
        Thread.sleep(3000);

        //CLICK CHECK BOX
        driver.findElement(By.xpath("//div[2]/label")).click();
        Thread.sleep(3000);
        
        

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

        //RANDOMLY SELECT REKSA DANA
        Select dropdown2 = new Select(driver.findElement(By.xpath("//div[@id='root']/form/div/section/div[3]/select")));
        List<WebElement> dd2 = dropdown2.getOptions();
        //int index2 = 0;
        int idx = 0;
        if (!dd2.isEmpty()) {
            Random rand = new Random();
            int rd = rand.nextInt(dd2.size() - 1);
            for (WebElement w : dd2) {
                if (rd == 0) {
                    System.out.println("RANDOM:" + idx);
                    dropdown2.selectByIndex(idx);
                } else {
                    System.out.println("RANDOM1:" + rd);
                    dropdown2.selectByIndex(rd);
                }
                idx++;
            }
        }
        Thread.sleep(2000);

        driver.findElement(By.xpath("//div[2]/div/label")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@id='root']/form/div/section[2]/button")).click();
        Thread.sleep(3000);

//
//        //CLICK BAYAR
//        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='keterangan ringkas'])[1]/following::button[1]")).click();
//        Thread.sleep(3000);
//
//        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Transfer Bank'])[1]/img[1]")).click();
//        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Tanpa bukti transfer dan Transaksi menggunakan kode unik yang akan ikut diinvestasikan.'])[1]/following::label[1]")).click();
//
//        Thread.sleep(3000);
//        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Segera transfer dana sejumlah total pembelian ke rekening reksa dana berikut:'])[1]/following::a[1]")).click();
//        Thread.sleep(3000);
//        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Keluar'])[1]/following::img[3]")).click();
//        Thread.sleep(3000);
//
//        //driver.findElement(By.xpath(".//*[normalize-space(text()) and normalize-space(.)='Home'])[1]/following::img[2]")).click();
//        //Thread.sleep(3000);
//        //driver.findElement(By.xpath(".//*[normalize-space(text()) and normalize-space(.)='Portfolio'])[1]/following::a[1]")).click();
//        driver.get("http://192.168.2.200:8083/#/history-transaction");
//        Thread.sleep(5000);
//
        Thread.sleep(5000);
//
//        driver.get("http://192.168.2.200:8083/#/logout");
        System.out.println("-----------------------------end of TEST SWITCHING !!!!!!!!!!!-----------------------------");
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

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.cache.disk.enable", false);
        profile.setPreference("browser.cache.memory.enable", false);
        profile.setPreference("browser.cache.offline.enable", false);
        profile.setPreference("network.http.use-cache", false);
        System.setProperty("webdriver.gecko.driver", readStream().getProperty("driver.gecko"));
        driver = new FirefoxDriver();
    }

    @AfterTest
    public void afterTest() {
//        driver.quit();
    }
}
