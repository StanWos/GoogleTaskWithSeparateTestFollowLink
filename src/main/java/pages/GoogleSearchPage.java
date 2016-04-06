package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static helpers.Helper.sizeOf;

public class GoogleSearchPage {

    public static String result = ".srg>.g";
    public static WebDriver driver;
    public static WebDriverWait wait;

    public void followNthLink(int index) {
        wait.until(sizeOf(By.cssSelector(result), 10));
        driver.findElements(By.cssSelector(result)).get(index).findElement(By.className("r")).click();
    }

}
