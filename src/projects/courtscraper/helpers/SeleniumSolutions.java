package courtscraper.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static courtscraper.FlowStart.driver;

public class SeleniumSolutions {

    public void waitUntilFound(String xpath) {
        while (true) {
            try {
                driver.findElement(By.xpath(xpath));
            } catch (NoSuchElementException e) {
                continue;
            }
            break;
        }
    }

    public boolean checkIfFound(String xpath) {
        try {
            driver.findElement(By.xpath(xpath));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
