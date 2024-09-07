package CourtScraper.Flows.Courtlink.CourtlinkSearchConfig;


import CourtScraper.Flows.Courtlink.CourtlinkMain;
import CourtScraper.Helpers.ProcessInputs;
import CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes;
import CourtScraper.StartGUI;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class CourtlinkSearchConfigMain extends CourtlinkMain {

    public static String[] processedInputs;

    private static WebElement dateBox;

    public static void courtLinkTermInputs() throws InterruptedException {

        Thread.sleep(1000);

        processedInputs = new ProcessInputs().grabInputs();

        //enter search terms
        courtLinkSearchTerms();

        //enter attorney name
        courtLinkAttorneyName();

        //select state
        courtLinkStateSelection();

        //select date
        courtLinkDateInput();

        Thread.sleep(1000);

        StartGUI.driver.findElement(By.xpath("//button[@id='triggersearch']")).click();
    }

    public static void courtLinkSearchTerms() throws InterruptedException {
        StartGUI.driver.findElement(By.xpath("//input[@name='keywords']")).sendKeys(processedInputs[0]);
        Thread.sleep(1000);

    }

    public static void courtLinkAttorneyName() throws InterruptedException {
        StartGUI.driver.findElement(By.cssSelector("div.controls-container:nth-child(1) > div:nth-child(5) > filteredtextbox:nth-child(1) > div:nth-child(1) > fieldset:nth-child(1) > div:nth-child(2) > div:nth-child(1) > input:nth-child(1)")).sendKeys(processedInputs[1]);
        Thread.sleep(1000);
    }

    public static void courtLinkStateSelection() throws InterruptedException {
        StartGUI.driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/div[1]/courtlist/div/courtlistselector/div/div[1]/span[1]/div")).click();
        StateSearchSelection.searchState(MainComboBoxes.selectedState);
        Thread.sleep(1000);
    }

    public static void courtLinkDateInput() throws InterruptedException {
        //scroll into view
        ((JavascriptExecutor) StartGUI.driver).executeScript("arguments[0].scrollIntoView(true);", StartGUI.driver.findElement(By.cssSelector("div.courtlinkdateselection-container")));
        Thread.sleep(1000);


        //select the date type
        Select dropdown = new Select(StartGUI.driver.findElement(By.xpath("//select")));
        dropdown.selectByVisibleText(MainComboBoxes.selectedDateType);

        Thread.sleep(1000);
        switch (MainComboBoxes.selectedDateType) {
            case "All available dates":
                return;
            case "Date is":
            case "Date is before":
            case "Date is after":
                dateBox = StartGUI.driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/segmentcontrols/div[1]/div[8]/dateselector/div/div[2]/div[2]/div/div/datepicker/div/input"));
                String date = processedInputs[2];
                String js = String.format("arguments[0].value = '%s';", date);
                StartGUI.driver.findElement(By.xpath("//input[@aria-label='Enter the date']")).sendKeys(processedInputs[2]);
                ((JavascriptExecutor) StartGUI.driver).executeScript(js, dateBox);
                break;
            case "Date is between":
                dateBox = StartGUI.driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/segmentcontrols/div[1]/div[8]/dateselector/div/div[2]/div[2]/div/div/datepicker/div/input"));
                ((JavascriptExecutor) StartGUI.driver).executeScript("arguments[0].value = '';", dateBox);
                Thread.sleep(500);
                StartGUI.driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/segmentcontrols/div[1]/div[8]/dateselector/div/div[2]/div[2]/div[1]/div[2]/datepicker/div/input")).sendKeys(processedInputs[2]);
                Thread.sleep(1000);
                dateBox = StartGUI.driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/segmentcontrols/div[1]/div[8]/dateselector/div/div[2]/div[2]/div[2]/div[2]/datepicker/div/input"));
                ((JavascriptExecutor) StartGUI.driver).executeScript("arguments[0].value = '';", dateBox);
                Thread.sleep(500);
                StartGUI.driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/segmentcontrols/div[1]/div[8]/dateselector/div/div[2]/div[2]/div[2]/div[2]/datepicker/div/input")).sendKeys(processedInputs[3]);
                break;
        }
    }

}