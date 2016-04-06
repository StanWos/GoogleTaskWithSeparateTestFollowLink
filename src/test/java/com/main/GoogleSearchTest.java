package com.main;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlContains;


public class GoogleSearchTest {

    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeClass
    public static void setUp() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 4);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void testSearch(){
        driver.get("https://www.google.com/?gws_rd=ssl#q=Selenium+automates+browsers");

        wait.until(sizeOf(By.cssSelector(result), 10));

        wait.until(textToBePresentInElementLocated(By.cssSelector(result + ":nth-child(1)"), "Selenium automates browsers"));
    }

    @Test
    public void testSearchAndFollowLink() {
        driver.get("https://www.google.com/?gws_rd=ssl#q=Selenium+automates+browsers");

        wait.until(sizeOf(By.cssSelector(result), 10));

        followNthLink(0);

        wait.until(urlContains("http://www.seleniumhq.org/"));
    }


    String result = ".srg>.g";

    public void followNthLink(int index) {
        driver.findElements(By.cssSelector(result)).get(index).findElement(By.className("r")).click();
    }

    public static ExpectedCondition<Boolean> sizeOf(final By elementsLocator, final int expectedSize) {
        return new ExpectedCondition<Boolean>() {
            private int listSize;
            private List<WebElement> elements;

            public Boolean apply(WebDriver driver) {
                elements = driver.findElements(elementsLocator);
                listSize = elements.size();
                return listSize == expectedSize;
            }

            public String toString() {
                return String.format("\nsize of list: %s\n to be: %s\n while actual size is: %s\n", elements, expectedSize, listSize);
            }
        };
    }
}