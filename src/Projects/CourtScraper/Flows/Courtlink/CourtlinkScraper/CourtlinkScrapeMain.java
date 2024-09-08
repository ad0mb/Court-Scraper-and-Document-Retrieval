package CourtScraper.Flows.Courtlink.CourtlinkScraper;

import CourtScraper.DataManagement.CSV.CSVSearchAppendTemp;
import CourtScraper.Flows.Courtlink.CourtlinkMain;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static CourtScraper.StartGUI.driver;


public class CourtlinkScrapeMain extends CourtlinkMain {

    //this is the main hub for the courtlink scrape flow

    private static WebDriverWait wait;
    private static int numCases = Integer.valueOf(driver.findElement(By.xpath("/html/body/main/div/main/div[2]/div/div[1]/div/div[1]/ul/li[1]")).getAttribute("data-actualresultscount"));

    public static void courtLinkScrape() throws IOException, InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Thread.sleep(500);
        System.out.println(numCases);
        driver.findElement(By.xpath("//*[@id=\"title_sr0\"]")).click();
        //waits until first page is loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Header\"]")));

        //Grabs Case Info
        for (int i = numCases-1; !(i == 0); i--) {
            //grabs info on page
            new CourtlinkScrapeMain().grabCaseInfo();
            Thread.sleep(2000);

            //grabs url for later next page loaded check
            String url = driver.getCurrentUrl();

            //clicks next button to move on to the next case
            driver.findElement(By.xpath("/html/body/main/div/main/div/form/div[1]/div[1]/ul[3]/li/button[2]/span[1]")).click();

            //ends if on last page to prevent stoppage and grabs last case before stopping
            if (i==1) {
                new CourtlinkScrapeMain().grabCaseInfo();
                Thread.sleep(2000);
                break;
            }
            //waits until next page loaded
            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
            Thread.sleep(500);


        }
    }

    public void grabCaseInfo() throws IOException {
        List<String> toBeAppended = new ArrayList<>();

        //inner text retrieves text not html from the inside and splits it

        String caseInfo = driver.findElement(By.xpath("//*[@id=\"Header\"]")).getAttribute("innerText");
        String[] info = caseInfo.split("\n");

        //adds the case number and date filed in a loop
        for (int i = 1; i<3; i++) {
            String[] elements = info[i].split(":", 2);
            toBeAppended.add(elements[1].trim());
        }
        //adds them to temporary csv file
        new CSVSearchAppendTemp().courtLinkAppendToTemp(toBeAppended);

    }
}