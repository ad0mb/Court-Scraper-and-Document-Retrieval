/**
 * @author Adam Bouloudene
 * @summary This class contains the main method which contains a selection of other methods which send the appropriate keys to each input box on the Courtlink serach configuration page.
 *
 * Methods:
 * courtLinkTermInputs: This method contains all the methods designed to input terms.
 * courtLinkSearchTerms: Inputs the main search terms into search terms box.
 * courtLinkAttorneyName: Inputs attorney name into attorney input box.
 * courtLinkStateSelection: Uses another method to properly input state names and selected county into the dropdown box for state/county selection.
 * courtLinkDateInput: Inputs date based on the selected date setting on the interface.
 *
 * @todo Properly comment out code (especially for date input).
 */

package courtscraper.flows.courtlink.courtlinksearchconfig;


import courtscraper.flows.courtlink.CourtlinkMain;
import courtscraper.helpers.guiinputprocessors.ProcessMainInputs;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static courtscraper.flows.courtlink.courtlinksearchconfig.StateSearchSelection.searchState;
import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.selectedDateType;
import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.selectedStateMain;


public class CourtlinkSearchConfigMain extends CourtlinkMain {

    //this is the main hub for the search configuration for courtlink flow

    public static String[] processedInputs;

    private static WebElement dateBox;

    public static void courtLinkTermInputs() throws InterruptedException {

        Thread.sleep(1000);

        processedInputs = new ProcessMainInputs().grabMainInputs();


        courtLinkSearchTerms(); // Enter search terms


        courtLinkAttorneyName(); // Enter attorney name


        courtLinkStateSelection(); // Select state


        courtLinkDateInput(); // Select date

        Thread.sleep(1000);

        driver.findElement(By.xpath("//button[@id='triggersearch']")).click();
    }

    private static void courtLinkSearchTerms() throws InterruptedException {
        driver.findElement(By.xpath("//input[@name='keywords']")).sendKeys(processedInputs[0]);
        Thread.sleep(1000);

    }

    private static void courtLinkAttorneyName() throws InterruptedException {
        driver.findElement(By.cssSelector("div.controls-container:nth-child(1) > div:nth-child(5) > filteredtextbox:nth-child(1) > div:nth-child(1) > fieldset:nth-child(1) > div:nth-child(2) > div:nth-child(1) > input:nth-child(1)")).sendKeys(processedInputs[1]);
        Thread.sleep(1000);
    }

    private static void courtLinkStateSelection() throws InterruptedException {
        driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/div[1]/courtlist/div/courtlistselector/div/div[1]/span[1]/div")).click();
        searchState(selectedStateMain);
        Thread.sleep(1000);
    }

    private static void courtLinkDateInput() throws InterruptedException {
        // Scroll into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector("div.courtlinkdateselection-container")));
        Thread.sleep(1000);


        // Select the date type
        Select dropdown = new Select(driver.findElement(By.xpath("//select")));
        dropdown.selectByVisibleText(selectedDateType);

        Thread.sleep(1000);
        switch (selectedDateType) {
            case "All available dates":
                return;
            case "Date is":
            case "Date is before":
            case "Date is after":
                dateBox = driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/segmentcontrols/div[1]/div[8]/dateselector/div/div[2]/div[2]/div/div/datepicker/div/input"));
                String date = processedInputs[2];
                String js = String.format("arguments[0].value = '%s';", date);
                driver.findElement(By.xpath("//input[@aria-label='Enter the date']")).sendKeys(processedInputs[2]);
                ((JavascriptExecutor) driver).executeScript(js, dateBox);
                break;
            case "Date is between":
                dateBox = driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/segmentcontrols/div[1]/div[8]/dateselector/div/div[2]/div[2]/div/div/datepicker/div/input"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", dateBox);
                Thread.sleep(500);
                driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/segmentcontrols/div[1]/div[8]/dateselector/div/div[2]/div[2]/div[1]/div[2]/datepicker/div/input")).sendKeys(processedInputs[2]);
                Thread.sleep(1000);
                dateBox = driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/segmentcontrols/div[1]/div[8]/dateselector/div/div[2]/div[2]/div[2]/div[2]/datepicker/div/input"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", dateBox);
                Thread.sleep(500);
                driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/segmentcontrols/div[1]/div[8]/dateselector/div/div[2]/div[2]/div[2]/div[2]/datepicker/div/input")).sendKeys(processedInputs[3]);
                break;
        }
    }

}
