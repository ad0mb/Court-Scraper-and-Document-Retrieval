package Projects.Flows.States.Texas.Harris;

import Projects.Flows.States.StateSelect;
import Projects.Helpers.ConfigGrabbers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.List;

import static Projects.Helpers.TabManagement.switchTab;
import static Projects.StartGUI.driver;

public class HarrisCounty extends StateSelect {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public void harrisMain(String caseNumber) throws FileNotFoundException, InterruptedException {

        //grabs harris site and logs in only if reset has recently happened
        if (driver.getCurrentUrl().equals("https://www.google.com/")) {
            loginHarris();
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='txtCaseNumber']"))).sendKeys(caseNumber);
        //driver.findElement(By.xpath("//*[@id='txtCaseNumber']")).sendKeys(caseNumber);

        Thread.sleep(3000);

        while (!driver.findElement(By.className("captcha-solver")).getAttribute("data-state").equals("solved")) {
            Thread.sleep(1);

            Thread.sleep(500);
            driver.findElement(By.cssSelector("#ctl00_ctl00_ctl00_ContentPlaceHolder1_ContentPlaceHolder2_ContentPlaceHolder2_btnCivSearch")).click();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//a[@title='View Case Details']")).click();
            Thread.sleep(500);

            switchTab(1);

            driver.findElement(By.xpath("//*[@id=\"tabDocuments\"]")).click();


            List<WebElement> rows = driver.findElements(By.xpath("/html/body/form/div[3]/div/div/div/div[3]/div[15]/div/section/div/div/div[3]/table/tbody/tr"));
            int count = 0;
            for (WebElement element : rows) {

                if (element.getAttribute("outerHTML").contains(">Order<") || count <= 5) {
                    WebElement link = element.findElement(By.xpath(".//a"));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", link);
                    link.click();
                    Thread.sleep(3000);

                    String name = element.findElement(By.xpath("./td/table/tbody/tr/td[1]")).getAttribute("innerHTML").replace("\n", " ").replace(",", "").replace("\u00A0", "").replace("/", " ").replace("\"", " ").trim();
                }
            }
        }
    }

    public static void loginHarris() throws InterruptedException, FileNotFoundException {
        driver.get("https://www.hcdistrictclerk.com/eDocs/Public/Search.aspx");
        String[] credentials = new ConfigGrabbers().loginGrabber("texasharris");

        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id='txtUserName']")).sendKeys(credentials[0]);
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id='txtPassword']")).sendKeys(credentials[1]);
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id='btnLogin']")).click();
        Thread.sleep(500);
    }
}