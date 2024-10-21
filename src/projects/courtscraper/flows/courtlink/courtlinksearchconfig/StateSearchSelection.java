/**
 * @author Adam Bouloudene
 * @summary This filters out which and what type of input to do to ensure it targest the proper county. This is used only in CourtlinkSearchConfigMain.
 *
 * @todo Clean up the code and make the same box be clicked and make switch cases purely for the input.
 */

package courtscraper.flows.courtlink.courtlinksearchconfig;

import org.openqa.selenium.By;

public class StateSearchSelection extends CourtlinkSearchConfigMain {

    //this is the dropdown selection portion of the courtlink search flow

    public static void searchState(String state) throws InterruptedException {
        String countyString = processedInputs[5];
        if (countyString.contains("SO")) {
            countyString = countyString.substring(0, countyString.length() - 3);
        }
        String inputString = "";
        switch (state) {
            case "California":
                inputString = state + " " + countyString;
                break;
            case "Florida":
                inputString = state + " " + countyString;
                break;
            case "Minnesota":
                inputString = state + " " + "State";
                break;
            case "Texas":
                inputString = state + " " + countyString + " district";
                break;
            case "New York":
                inputString = state + " " + countyString;
                break;
        }

        driver.findElement(By.xpath("//*[@id=\"courtsdropdown\"]")).sendKeys(inputString);
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/div[1]/courtlist/div/courtlistselector/div/div[2]/div/div[2]/div/div/div/div/label/div")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/segmentcontrols/div[1]/div[1]/div[2]/input")).click();
    }
}
