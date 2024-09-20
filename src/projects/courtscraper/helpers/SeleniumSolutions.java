package courtscraper.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static courtscraper.StartGUI.driver;

public class SeleniumSolutions {

    public static void waitUntilFound(String xpath) {
        while (true) {
            try {
                driver.findElement(By.xpath(xpath));
            } catch (NoSuchElementException e) {
                continue;
            }
            break;
        }
    }
}
