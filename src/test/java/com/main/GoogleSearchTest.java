package com.main;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.GoogleSearchPage;

import static helpers.Helper.sizeOf;
import static pages.GoogleSearchPage.result;
import static pages.GoogleSearchPage.driver;
import static pages.GoogleSearchPage.wait;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;


public class GoogleSearchTest {

    GoogleSearchPage googlePage = new GoogleSearchPage();

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 8);

        driver.get("https://www.google.com/?gws_rd=ssl#q=Selenium+automates+browsers");
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void testSearch(){
        wait.until(sizeOf(By.cssSelector(result), 10));
        wait.until(textToBePresentInElementLocated(By.cssSelector(result + ":nth-child(1)"), "Selenium automates browsers"));
    }

    @Test
    public void testSearchAndFollowLink() {
        googlePage.followNthLink(0);
        wait.until(urlContains("http://www.seleniumhq.org/"));
    }

}