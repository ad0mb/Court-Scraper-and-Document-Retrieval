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
        String inputString = "";
        switch (state) {
            case "Florida":
                inputString = state + " " + processedInputs[5];
                break;
            case "Texas":
                inputString = state + " " + processedInputs[5] + " district";
                break;
        }

        driver.findElement(By.xpath("//*[@id=\"courtsdropdown\"]")).sendKeys(inputString);
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/div[1]/courtlist/div/courtlistselector/div/div[2]/div/div[2]/div/div/div/div/label/div")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/segmentcontrols/div[1]/div[1]/div[2]/input")).click();
    }
}
