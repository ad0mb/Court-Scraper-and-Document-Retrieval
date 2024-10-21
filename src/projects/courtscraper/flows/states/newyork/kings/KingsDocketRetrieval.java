package courtscraper.flows.states.newyork.kings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static courtscraper.helpers.CheckIfRetrieved.fileDownloadedCheck;
import static courtscraper.helpers.FileManagement.tempFileRename;

public class KingsDocketRetrieval extends KingsCounty{

    public void retrieveDockets() throws InterruptedException {

        List<WebElement> rows = driver.findElements(By.xpath("//tbody/tr"));

        WebElement link;
        int count = 1;
        for (WebElement element : rows) {
            link = element.findElement(By.xpath(".//td[2]//span"));
            String name = element.findElement(By.xpath(".//td[2]//span")).getText();

            link.click();

            boolean hasLink = selSolutions.checkIfFound(String.format("/html/body/div/div[3]/div[2]/div/form/div/div[3]/table/tbody/tr[%s]//a", count));
            if (hasLink) {
                fileDownloadedCheck("caseDetail");
                tempFileRename("caseDetail", name);
                Thread.sleep(1500);
            }
            count++;
        }
    }
}
