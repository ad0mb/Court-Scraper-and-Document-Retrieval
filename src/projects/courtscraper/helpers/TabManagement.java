package courtscraper.helpers;

import org.openqa.selenium.JavascriptExecutor;

import java.util.Set;

import static courtscraper.StartGUI.driver;


public class TabManagement {

    //this file contains all the methods that allow you to manipulate and modify browser tabs as needed

    public static void closeAllTabs() throws InterruptedException {
        Set<String> tabs = driver.getWindowHandles();
        ((JavascriptExecutor) driver).executeScript("window.open('https://www.google.com');");
        for (String tab : tabs) {
            driver.switchTo().window(tab);
            driver.close();

        }
        switchTab(0);
   }

   public static void switchTab(int tabSpot) {
        Set<String> tabs = driver.getWindowHandles();

        int count = 0;
        for (String tab : tabs) {
            if (count == tabSpot) {
                driver.switchTo().window(tab);
                break;
            }
            count++;
        }
   }

   public static void closeLastOpened() {
        String currentTab = driver.getWindowHandle();
        int numOfTabs = driver.getWindowHandles().size();
        switchTab(numOfTabs-1);
        driver.close();
        driver.switchTo().window(currentTab);
   }
}
