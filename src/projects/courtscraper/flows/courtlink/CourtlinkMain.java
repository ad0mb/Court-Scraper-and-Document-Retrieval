/**
 * @author Adam Bouloudene
 * @summary This class is the hub for the entire Courtlink process from login to scrape.
 *
 * Methods:
 * courtLinkFlow: This method contains the search configuration and scraping processes in order.
 * courtLinkLogin: Login process to log into the homepage of Courtlink
 */

package courtscraper.flows.courtlink;

import courtscraper.FlowStart;
import courtscraper.datamanagement.json.JSONGrabbers;
import org.openqa.selenium.By;

import java.io.FileNotFoundException;
import java.io.IOException;

import static courtscraper.flows.courtlink.courtlinkscraper.CourtlinkScrapeMain.courtLinkScrape;
import static courtscraper.flows.courtlink.courtlinksearchconfig.CourtlinkSearchConfigMain.courtLinkTermInputs;

public class CourtlinkMain extends FlowStart {

    public static void courtLinkFlow() throws IOException, InterruptedException {
        courtLinkLogin(); // Login process

        Thread.sleep(1000);


        courtLinkTermInputs(); // Inputs terms/dates into input boxes

        Thread.sleep(1000);


        courtLinkScrape(); //Scrapes Courtlink for case numbers


    }


    private static void courtLinkLogin() throws FileNotFoundException, InterruptedException {
        driver.get("https://signin.lexisnexis.com/lnaccess/app/signin?back=https%3A%2F%2Fadvance.lexis.com%3A443%2Fcourtlinkhome&aci=la");
        String[] credentials = new JSONGrabbers().loginGrabber("Courtlink");

        driver.findElement(By.xpath("//*[@id=\"userid\"]")).sendKeys(credentials[0]);
        driver.findElement(By.xpath("//*[@id=\"signInSbmtBtn\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(credentials[1]);
        driver.findElement(By.xpath("//*[@id=\"signInSbmtBtn\"]")).click();
    }
}
