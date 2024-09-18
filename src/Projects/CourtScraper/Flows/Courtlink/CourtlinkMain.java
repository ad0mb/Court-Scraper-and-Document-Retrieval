package CourtScraper.Flows.Courtlink;

import CourtScraper.DataManagement.JSON.JSONGrabbers;
import CourtScraper.StartGUI;
import org.openqa.selenium.By;

import java.io.FileNotFoundException;
import java.io.IOException;


import static CourtScraper.Flows.Courtlink.CourtlinkScraper.CourtlinkScrapeMain.courtLinkScrape;
import static CourtScraper.Flows.Courtlink.CourtlinkSearchConfig.CourtlinkSearchConfigMain.courtLinkTermInputs;

public class CourtlinkMain {

    //main hub for all courtlink processes from login, to search, to scrape

    public void CourtlinkFlow() throws IOException, InterruptedException {
        //Login process
        courtLinkLogin();

        Thread.sleep(1000);

        //inputs terms/dates into input boxes
        courtLinkTermInputs();

        Thread.sleep(1000);

        //scrapes courtlink for casenumbers
        courtLinkScrape();


    }


    public static void courtLinkLogin() throws FileNotFoundException, InterruptedException {
        StartGUI.driver.get("https://signin.lexisnexis.com/lnaccess/app/signin?back=https%3A%2F%2Fadvance.lexis.com%3A443%2Fcourtlinkhome&aci=la");
        String[] credentials = new JSONGrabbers().loginGrabber("courtlink");

        StartGUI.driver.findElement(By.xpath("//*[@id=\"userid\"]")).sendKeys(credentials[0]);
        StartGUI.driver.findElement(By.xpath("//*[@id=\"signInSbmtBtn\"]")).click();
        Thread.sleep(1500);
        StartGUI.driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(credentials[1]);
        StartGUI.driver.findElement(By.xpath("//*[@id=\"signInSbmtBtn\"]")).click();
    }
}
