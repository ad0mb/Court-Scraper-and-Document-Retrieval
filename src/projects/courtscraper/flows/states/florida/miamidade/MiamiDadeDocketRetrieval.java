/**
 * @author Adam Bouloudene
 * @summary This class is the specific Miami-Dade County flow that will actually download and organize files retrieves from each case.
 *
 * Methods:
 * retrieveDockets: Loops through the format of the page to sort through which documents it wants to download.
 */

package courtscraper.flows.states.florida.miamidade;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static courtscraper.helpers.CheckIfRetrieved.fileDownloadedCheck;
import static courtscraper.helpers.FileManagement.tempFileRename;
import static courtscraper.helpers.TabManagement.closeLastOpened;

public class MiamiDadeDocketRetrieval extends MiamiDadeCounty {

    public void retrieveDockets() throws InterruptedException {

        List<WebElement> rows = driver.findElements(By.xpath("/html/body/main/form/div[4]/div[2]/div/div[8]/div[2]/table/tbody/tr"));

        WebElement link;
        for (WebElement element : rows) {
            // Grabs link to click and download and grabs name
            link = element.findElement(By.xpath(".//td[1]"));
            String name = element.findElement(By.xpath(".//td[5]")).getAttribute("innerHTML").replace("\n", " ").replace(",", "").replace("\u00A0", "").replace("/", " ").replace("\"", " ").trim();

            link.click();
            // If more than one tab opened means that download has been started
            boolean Downloaded = driver.getWindowHandles().size() > 1;
            if (Downloaded) {
                fileDownloadedCheck("Document");
                tempFileRename("Document", name);
                closeLastOpened();
            }
        }
    }
}
