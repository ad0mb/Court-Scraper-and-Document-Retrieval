package courtscraper.flows.states.newyork.kings;

import courtscraper.flows.states.StateSelect;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.time.Duration;

public class KingsCounty extends StateSelect {

    private static WebDriverWait wait;

    public static void kingsMain(String caseNumber) throws FileNotFoundException, InterruptedException {
        //captchaSolver = new CaptchaSolver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        if (driver.getCurrentUrl().equals("https://www.google.com/")) {
            driver.get("https://iapps.courts.state.ny.us/webccos/kingscc/indexSearch");
        }
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='id5']"))).sendKeys(caseNumber.split("/")[0]);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='id6']"))).sendKeys(caseNumber.split("/")[1]);

        if (selSolutions.checkIfFound("//div[contains(@style, '78px;')]")) {
            captchaSolver.solveReCaptcha(driver.findElement(By.xpath("//*[@id=\"id3\"]/div/div[4]/div")).getAttribute("data-sitekey"), driver.getCurrentUrl());
        }

        driver.findElement(By.xpath("//*[@id='id11']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@style='white-space:nowrap']"))).click();

        new KingsDocketRetrieval().retrieveDockets();

        driver.get("https://iapps.courts.state.ny.us/webccos/kingscc/indexSearch");
    }
}