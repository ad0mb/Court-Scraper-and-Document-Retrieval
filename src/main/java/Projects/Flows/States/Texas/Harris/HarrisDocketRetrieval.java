package Projects.Flows.States.Texas.Harris;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

import static Projects.Helpers.TabManagement.closeLastOpened;
import static Projects.StartGUI.driver;

public class HarrisDocketRetrieval extends HarrisCounty {

    public void retrieveDockets() throws InterruptedException {

        List<WebElement> rows = driver.findElements(By.xpath("/html/body/form/div[3]/div/div/div/div[3]/div[15]/div/section/div/div/div[3]/table/tbody/tr"));
        int count = 0;
        for (WebElement element : rows) {
            count++;
            if (element.getAttribute("outerHTML").contains(">Order<") || count <= 5) {
                //finds anchor for the link to click and download
                WebElement link = element.findElement(By.xpath(".//a"));

                //grabs name of document for renaming purposes
                String name = element.findElement(By.xpath("./td/table/tbody/tr/td[1]")).getAttribute("innerHTML").replace("\n", " ").replace(",", "").replace("\u00A0", "").replace("/", " ").replace("\"", " ").trim();

                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
                link.click();


                Thread.sleep(5000);
                closeLastOpened();


            }
        }
    }
}
