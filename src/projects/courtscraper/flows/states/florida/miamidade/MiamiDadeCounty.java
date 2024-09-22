package courtscraper.flows.states.florida.miamidade;

import courtscraper.flows.states.StateSelect;
import courtscraper.datamanagement.json.JSONGrabbers;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MiamiDadeCounty extends StateSelect {

    private static WebDriverWait wait;
    //for case type dropdown
    public static Map<String, String> caseType = Map.of(
            "AC", "AC - Civil Appeals",
            "AF", "AF - Administrative File",
            "AP", "AP - Civil Appeals",
            "CA", "CA - Circuit Civil",
            "CC", "CC - County Civil",
            "CP", "CP - Circuit Probate",
            "FC", "FC - Family Civil",
            "GD", "GD - Guardianship",
            "MH", "MH - Mental Health",
            "SP", "SP - Summary Procedure");

    public static void miamiDadeMain(String caseNumber) throws InterruptedException, FileNotFoundException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //grabs miami-dade site and logs in only if reset has recently happened
        if (driver.getCurrentUrl().equals("https://www.google.com/")) {
            loginMiamiDade();
            driver.get("https://www2.miamidadeclerk.gov/ocs/");
            Thread.sleep(500);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_lnkStandardSearch2\"]"))).click();
            Thread.sleep(500);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/form/div[4]/div[2]/div/div[5]/div/div/div[1]/ul/li[2]/a"))).click();
        }
        //creates case numbers list
        List<String> caseNumbers = new ArrayList<>(List.of(caseNumber.split("-")));
        //each block enters a parameter for the search
        driver.findElement(By.xpath("//*[@id=\"txtLCNYearSTD_localCaseContent\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"txtLCNYearSTD_localCaseContent\"]")).sendKeys(caseNumbers.get(0));

        driver.findElement(By.xpath("//*[@id=\"txtLCNSeqSTD_localCaseContent\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"txtLCNSeqSTD_localCaseContent\"]")).sendKeys(caseNumbers.get(1));

        Select caseCodes = new Select(driver.findElement(By.xpath("//*[@id=\"localCaseCodesSelect_localCaseContent\"]")));
        caseCodes.selectByVisibleText(caseType.get(caseNumbers.get(2)));

        driver.findElement(By.xpath("//*[@id=\"txtLCNLocSTD_localCaseContent\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"txtLCNLocSTD_localCaseContent\"]")).sendKeys(caseNumbers.get(3));

        driver.findElement(By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_btnlocalCaseTab\"]")).click();
        Thread.sleep(500);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/form/div[4]/div[2]/div/div[8]/div[1]"))).click();

        new MiamiDadeDocketRetrieval().retrieveDockets();

        driver.findElement(By.xpath("//*[@id=\"ctl00_ContentPlaceHolder1_lnkbtn_DetailsBottom\"]")).click();
    }

    private static void loginMiamiDade() throws InterruptedException, FileNotFoundException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://www2.miamidadeclerk.gov/usermanagementservices");
        String[] credentials = new JSONGrabbers().loginGrabber("FloridaMiami Dade");

        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"userName\"]")).sendKeys(credentials[0]);
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(credentials[1]);
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div/div/main/form/div[2]/div[1]/div[2]/div[3]/input[1]")).click();
        //waits until logged in
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div/div/div/div[1]/div/div[3]")));
    }
}
