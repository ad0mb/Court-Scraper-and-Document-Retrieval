package Projects.Flows.Courtlink;

import Projects.Helpers.ConfigGrabbers;
import org.openqa.selenium.By;

import java.io.FileNotFoundException;
import java.io.IOException;


import static Projects.Flows.Courtlink.CourtlinkScraper.CourtlinkScrapeMain.courtLinkScrape;
import static Projects.Flows.Courtlink.CourtlinkSearchConfig.CourtlinkSearchConfigMain.courtLinkTermInputs;
import static Projects.StartGUI.driver;

public class CourtlinkMain {

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
        driver.get("https://signin.lexisnexis.com/lnaccess/app/signin?back=https%3A%2F%2Fadvance.lexis.com%3A443%2Fcourtlinkhome&aci=la");
        String[] credentials = new ConfigGrabbers().loginGrabber("courtlink");

        driver.findElement(By.xpath("//*[@id=\"userid\"]")).sendKeys(credentials[0]);
        driver.findElement(By.xpath("//*[@id=\"signInSbmtBtn\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(credentials[1]);
        driver.findElement(By.xpath("//*[@id=\"signInSbmtBtn\"]")).click();
    }
}
