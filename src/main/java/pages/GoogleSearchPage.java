package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class GoogleSearchPage {

    public static String result = ".srg>.g";
    public static WebDriver driver;

    public void followNthLink(int index) {
        driver.findElements(By.cssSelector(result)).get(index).findElement(By.className("r")).click();
    }

    public void search(String searchText){
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys(searchText + Keys.ENTER);
    }

}
