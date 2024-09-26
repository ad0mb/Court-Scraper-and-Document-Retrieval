package courtscraper.flows.courtlink.courtlinkscraper;

import courtscraper.flows.courtlink.CourtlinkMain;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import static courtscraper.datamanagement.csv.CSVSearchAppendTemp.courtLinkAppendToTemp;


public class CourtlinkScrapeMain extends CourtlinkMain {

    //this is the main hub for the courtlink case number collection flow

    private static WebDriverWait wait;
    private static int numCases = getNumCases(); //number of cases defined at start

    public static void courtLinkScrape() throws IOException, InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Thread.sleep(500);
        System.out.println(numCases);

        //grabs Case Info
        for (int i = 0; i<numCases; i++) {
            driver.navigate().refresh();
            Thread.sleep(2000);
            System.out.println(i);
            new CourtlinkScrapeMain().grabCaseInfo(); //grabs info on page
            Thread.sleep(2000);

            String url = driver.getCurrentUrl(); //grabs url for later next page loaded check


            //ends if on last page to prevent stoppage and grabs last case before stopping
            if (i==numCases-1) {
                Thread.sleep(2000);
                break;
            }

            driver.findElement(By.cssSelector("a.la-TriangleRight")).click();  //clicks next button to move on to the next page

            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(url))); //waits until next page loaded
            Thread.sleep(500);


        }
    }

    private void grabCaseInfo() throws IOException {
        WebElement item = driver.findElement(By.xpath("/html/body/main/script[2]")); //grabs Javascript script
        Pattern pattern = Pattern.compile("(?<=\"docketnumber\":)(\".*?\")*|(?<=\"date\":)(\".*?\")*"); //regex that grabs the date and docketnumber from javascript script
        String[] pageInfo = pattern.matcher(item.getAttribute("innerHTML")).results().map(MatchResult::group).toArray(String[]::new);

        //adds the case number and date filed in a loop
        for (int i = 0; i<pageInfo.length; i+=2) {
            List<String> toBeAppended = new ArrayList<>();
            toBeAppended.add(pageInfo[i]);
            toBeAppended.add(pageInfo[i + 1]);
            courtLinkAppendToTemp(toBeAppended);
        }

    }

    private static int getNumCases() {
        if (!driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/form/div[2]/nav/ol/li[7]/a")).isEmpty()) {
            return Integer.valueOf(driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/form/div[2]/nav/ol/li[6]/a")).getAttribute("data-value"));
        } else if (!driver.findElements(By.xpath("//*[@id=\"content\"]/div[2]/form/div[2]/nav/ol/li[4]/a")).isEmpty()) {
            List<WebElement> list = driver.findElements(By.xpath("/html/body/main/div/main/div[2]/div/div[2]/div[2]/form/div[2]/nav/ol/li"));
            return Integer.valueOf(list.get(list.size()-2).findElement(By.xpath(".//a")).getAttribute("data-value"));
        } else {
            return Integer.valueOf(driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/form/div[2]/nav/ol/li[2]/span")).getAttribute("innerHTML"));
        }
    }
}
