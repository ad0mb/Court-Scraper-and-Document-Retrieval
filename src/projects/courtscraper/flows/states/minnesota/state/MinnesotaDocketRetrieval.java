package courtscraper.flows.states.minnesota.state;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MinnesotaDocketRetrieval extends MinnesotaState {

    public void retrieveDockets() throws InterruptedException {
        int pgAmt = Integer.parseInt(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/div/div[1]/div[2]/div[3]/div/div/div[4]/div/div[1]"))).getAttribute("data-date-sort"));
        if (pgAmt > 15) {pgAmt = 15;}

        System.out.println(pgAmt);
        for (int i = pgAmt; i>=1; i--) {
            String name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("/html/body/main/div/div[1]/div[2]/div[3]/div/div/div[4]/div/div[%d]//*[@class=\"mpa-text-jet\"]", i)))).getText();
            System.out.println(name);
            WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format("/html/body/main/div/div[1]/div[2]/div[3]/div/div/div[4]/div/div[%d]/div[2]/div[2]/div/form", i))));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
            link.click();
            Thread.sleep(1500);
        }

    }
}
