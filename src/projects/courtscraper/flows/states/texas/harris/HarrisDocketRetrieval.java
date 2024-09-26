/**
 * @author Adam Bouloudene
 * @summary This class is the specific Harris County flow that will actually download and organize files retrieves from each case.
 *
 */

package courtscraper.flows.states.texas.harris;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

import static courtscraper.helpers.CheckIfRetrieved.fileDownloadedCheck;
import static courtscraper.helpers.TabManagement.closeLastOpened;

public class HarrisDocketRetrieval extends HarrisCounty {


    public void retrieveDockets() throws InterruptedException {

        List<WebElement> rows = driver.findElements(By.xpath("/html/body/form/div[3]/div/div/div/div[3]/div[15]/div/section/div/div/div[3]/table/tbody/tr"));
        int count = 0;
        // Defines link for compatibility with the try catch for grabbing the link below
        WebElement link;
        for (WebElement element : rows) {
            count++;
            if (element.getAttribute("outerHTML").contains(">Order<") || count <= 5) {
                // Finds anchor for the link to click and download
                try {
                    link = element.findElement(By.xpath(".//a"));
                } catch (Exception e) {
                    continue;
                }

                docketNames.add(element.findElement(By.xpath("./td/table/tbody/tr/td[1]")).getAttribute("innerHTML").replace("\n", " ").replace(",", "").replace("\u00A0", "").replace("/", " ").replace("\"", " ").trim()); // Grabs name of document for renaming purposes

                docketNumbers.add(link.getText());
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link); // Grabs docket number for renaming and puts them on a list for later use

                try {
                    link.click();
                } catch (Exception ignored) {}

                Thread.sleep(2000);
                fileDownloadedCheck(link.getText());

                closeLastOpened();


            }
        }
    }
}
