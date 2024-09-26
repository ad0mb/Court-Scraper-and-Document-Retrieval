package courtscraper.flows.courtlink;

import courtscraper.FlowStart;
import courtscraper.datamanagement.json.JSONGrabbers;
import org.openqa.selenium.By;

import java.io.FileNotFoundException;
import java.io.IOException;

import static courtscraper.flows.courtlink.courtlinkscraper.CourtlinkScrapeMain.courtLinkScrape;
import static courtscraper.flows.courtlink.courtlinksearchconfig.CourtlinkSearchConfigMain.courtLinkTermInputs;

public class CourtlinkMain extends FlowStart {

    //main hub for all courtlink processes from login, to search, to scrape

    public static void CourtlinkFlow() throws IOException, InterruptedException {
        courtLinkLogin(); //login process

        Thread.sleep(1000);

        courtLinkTermInputs(); //inputs terms/dates into input boxes

        Thread.sleep(1000);

        courtLinkScrape(); //scrapes courtlink for casenumbers


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
