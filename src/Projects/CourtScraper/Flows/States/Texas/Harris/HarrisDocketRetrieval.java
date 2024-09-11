package CourtScraper.Flows.States.Texas.Harris;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.security.spec.ECField;
import java.util.List;
import java.util.NoSuchElementException;

import static CourtScraper.Helpers.TabManagement.closeLastOpened;
import static CourtScraper.StartGUI.driver;

public class HarrisDocketRetrieval extends CourtScraper.Flows.States.Texas.Harris.HarrisCounty {


    public void retrieveDockets() throws InterruptedException {

        List<WebElement> rows = driver.findElements(By.xpath("/html/body/form/div[3]/div/div/div/div[3]/div[15]/div/section/div/div/div[3]/table/tbody/tr"));
        int count = 0;
        //defines link for compatibility with the try catch for grabbing the link below
        WebElement link;
        for (WebElement element : rows) {
            count++;
            if (element.getAttribute("outerHTML").contains(">Order<") || count <= 5) {
                //finds anchor for the link to click and download
                try {
                    link = element.findElement(By.xpath(".//a"));
                } catch (Exception e) {
                    continue;
                }
                //grabs name of document for renaming purposes
                docketNames.add(element.findElement(By.xpath("./td/table/tbody/tr/td[1]")).getAttribute("innerHTML").replace("\n", " ").replace(",", "").replace("\u00A0", "").replace("/", " ").replace("\"", " ").trim());
                //grabs docket number for renaming and puts them on a list for later use
                docketNumbers.add(link.getText());
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
                try {
                    link.click();
                } catch (Exception ignored) {}


                Thread.sleep(5000);
                closeLastOpened();


            }
        }
    }
}
