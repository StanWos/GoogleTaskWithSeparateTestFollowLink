package com.main;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.GoogleSearchPage;

import static helpers.Helper.sizeOf;
import static pages.GoogleSearchPage.result;
import static pages.GoogleSearchPage.driver;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;


public class GoogleSearchTest {

    static WebDriverWait wait;

    GoogleSearchPage googlePage = new GoogleSearchPage();

    @BeforeClass
    public static void setUp() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 4);

        driver.get("http://google.com/ncr");
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void testSearch(){
        googlePage.search("Selenium automates browsers");
        wait.until(sizeOf(By.cssSelector(result), 10));
        wait.until(textToBePresentInElementLocated(By.cssSelector(result + ":nth-child(1)"), "Selenium automates browsers"));
    }

    @Test
    public void testSearchAndFollowLink() {
        googlePage.search("Selenium automates browsers");
        googlePage.followNthLink(0);
        wait.until(urlContains("http://www.seleniumhq.org/"));
    }

}