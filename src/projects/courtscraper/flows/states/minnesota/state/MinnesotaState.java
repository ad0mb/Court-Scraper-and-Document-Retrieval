package courtscraper.flows.states.minnesota.state;

import courtscraper.flows.states.StateParser;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MinnesotaState extends StateParser {

    public static WebDriverWait wait;

    public static void minnesotaMain(String caseNumber) throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        driver.get("https://publicaccess.courts.state.mn.us/DocumentSearch");

        if (driver.findElement(By.xpath("//*[@id=\"tcModalAcceptBtn\"]")).isDisplayed()) {
            driver.findElement(By.xpath("//*[@id=\"tcModalAcceptBtn\"]")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"CaseNumber\"]"))).sendKeys(caseNumber);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"btnFindCaseByNumber\"]"))).click();
        }

        while (!selSolutions.checkIfFound("/html/body/main/div/div[1]/div[2]/div[2]/h2")) {
            driver.navigate().refresh();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"CaseNumber\"]"))).sendKeys(caseNumber);
            Thread.sleep(500);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"btnFindCaseByNumber\"]"))).click();
            Thread.sleep(4000);
        }

        Thread.sleep(2000);

        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"CaseNumber\"]"))).sendKeys(caseNumber);

        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"btnFindCaseByNumber\"]"))).click();

        new MinnesotaDocketRetrieval().retrieveDockets();

        Thread.sleep(4000);
    }
}
