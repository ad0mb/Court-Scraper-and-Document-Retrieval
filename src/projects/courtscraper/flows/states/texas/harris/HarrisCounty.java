package courtscraper.flows.states.texas.harris;

import courtscraper.datamanagement.json.JSONGrabbers;
import courtscraper.exceptions.BlockedDocumentException;
import courtscraper.flows.states.StateSelect;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.time.Duration;

import static courtscraper.helpers.TabManagement.switchTab;


public class HarrisCounty extends StateSelect {

    private static WebDriverWait wait;

    public static void harrisMain(String caseNumber) throws FileNotFoundException, InterruptedException, BlockedDocumentException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        //grabs harris site and logs in only if reset has recently happened (aka. Google webpage)
        if (driver.getCurrentUrl().equals("https://www.google.com/")) {
            loginHarris();
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='txtCaseNumber']"))).sendKeys(caseNumber); //inputs case number

        Thread.sleep(6000);

        try {
            while (!driver.findElement(By.className("captcha-solver")).getAttribute("data-state").equals("solved")) {
                Thread.sleep(1);
            }
        } catch (NoSuchElementException e) {
            System.out.println("no captcha");
        }

        Thread.sleep(500);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#ctl00_ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder2_ContentPlaceHolder2_btnCivSearch"))).click(); //clicks search to search entered case number
        Thread.sleep(1500);

        //looks for confidential document tag on website and throws special exception if it is blocked and clicks search again.
        try {
            driver.findElement(By.xpath("//*[@class=\"systemmessage\"]"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ctl00_ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder2_ContentPlaceHolder2_btnSearchAgain\"]"))).click();
            throw new BlockedDocumentException();
        } catch (NoSuchElementException ignored) {}

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='View Case Details']"))).click(); //clicks on case after search
        Thread.sleep(2000);

        switchTab(1);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"tabDocuments\"]"))).click(); //clicks on tab containing documents

        new HarrisDocketRetrieval().retrieveDockets(); //starts harris retrieval flow

        driver.close();
        switchTab(0);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ctl00_ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder2_ContentPlaceHolder2_btnSearchAgain\"]"))).click(); //clicks search again to complete the loop

        renameFilesBulk(); //renames case files
    }

    private static void loginHarris() throws InterruptedException, FileNotFoundException {
        driver.get("https://www.hcdistrictclerk.com/eDocs/Public/Search.aspx");
        String[] credentials = new JSONGrabbers().loginGrabber("TexasHarris");

        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id='txtUserName']")).sendKeys(credentials[0]);
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id='txtPassword']")).sendKeys(credentials[1]);
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id='btnLogin']")).click();
        Thread.sleep(500);
    }
}