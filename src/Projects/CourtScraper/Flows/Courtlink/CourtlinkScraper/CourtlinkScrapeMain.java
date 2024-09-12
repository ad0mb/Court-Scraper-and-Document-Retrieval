package CourtScraper.Flows.Courtlink.CourtlinkScraper;

import CourtScraper.DataManagement.CSV.CSVSearchAppendTemp;
import CourtScraper.Flows.Courtlink.CourtlinkMain;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import static CourtScraper.StartGUI.driver;


public class CourtlinkScrapeMain extends CourtlinkMain {

    //this is the main hub for the courtlink scrape flow

    private static WebDriverWait wait;
    private static int numCases = Integer.valueOf(driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/form/div[2]/nav/ol/li[6]/a")).getAttribute("data-value"));

    public static void courtLinkScrape() throws IOException, InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Thread.sleep(500);
        System.out.println(numCases);
        //waits until first page is loaded

        //Grabs Case Info
        for (int i = 0; i<numCases; i++) {
            driver.navigate().refresh();
            Thread.sleep(2000);
            System.out.println(i);
            //grabs info on page
            new CourtlinkScrapeMain().grabCaseInfo();
            Thread.sleep(2000);

            //grabs url for later next page loaded check
            String url = driver.getCurrentUrl();


            //ends if on last page to prevent stoppage and grabs last case before stopping
            if (i==numCases-1) {
                Thread.sleep(2000);
                break;
            }

            //clicks next button to move on to the next page
            driver.findElement(By.cssSelector("a.la-TriangleRight")).click();

            //waits until next page loaded
            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url)));
            Thread.sleep(500);


        }
    }

    private void grabCaseInfo() throws IOException {
        //grabs Javascript script
        WebElement item = driver.findElement(By.xpath("/html/body/main/script[2]"));
        //regex that grabs the date and docketnumber from javascript script
        Pattern pattern = Pattern.compile("(?<=\"docketnumber\":)(\".*?\")*|(?<=\"date\":)(\".*?\")*");
        String[] pageInfo = pattern.matcher(item.getAttribute("innerHTML")).results().map(MatchResult::group).toArray(String[]::new);

        //adds the case number and date filed in a loop
        for (int i = 0; i<pageInfo.length; i+=2) {
            List<String> toBeAppended = new ArrayList<>();
            toBeAppended.add(pageInfo[i]);
            toBeAppended.add(pageInfo[i + 1]);
            new CSVSearchAppendTemp().courtLinkAppendToTemp(toBeAppended);
        }

    }
}