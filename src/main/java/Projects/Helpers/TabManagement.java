package Projects.Helpers;

import org.openqa.selenium.JavascriptExecutor;

import java.util.Set;

import static Projects.StartGUI.driver;

public class TabManagement {

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
            }
        }
   }
}
