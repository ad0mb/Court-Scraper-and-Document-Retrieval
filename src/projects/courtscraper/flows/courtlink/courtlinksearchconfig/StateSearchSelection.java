package courtscraper.flows.courtlink.courtlinksearchconfig;

import courtscraper.setups.gui.mainpanelelements.MainComboBoxes;
import org.openqa.selenium.By;

import static courtscraper.StartGUI.driver;

public class StateSearchSelection extends CourtlinkSearchConfigMain {

    //this is the dropdown selection portion of the courtlink search flow

    public static void searchState(String state) throws InterruptedException {
        switch (state) {
            case "California":
                driver.findElement(By.xpath("//*[@id=\"courtsdropdown\"]")).sendKeys("California " + MainComboBoxes.selectedCountyMain);
                driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/div[1]/courtlist/div/courtlistselector/div/div[2]/div/div[2]/div/div/div/div/label/div")).click();
                break;
            case "Florida":
                driver.findElement(By.xpath("//*[@id=\"courtsdropdown\"]")).sendKeys("Florida " + MainComboBoxes.selectedCountyMain);
                driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/div[1]/courtlist/div/courtlistselector/div/div[2]/div/div[2]/div/div/div/div/label/div")).click();
                break;
            case "New York":
                if (MainComboBoxes.selectedCountyMain.equals("New York BKQ")) {
                    String[] BKQ = {"Bronx", "Kings", "Queens"};
                    for (String burrough : BKQ) {
                        driver.findElement(By.xpath("//*[@id=\"courtsdropdown\"]")).sendKeys("New York " + burrough);
                        driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/div[1]/courtlist/div/courtlistselector/div/div[2]/div/div[2]/div/div/div/div/label/div")).click();
                        driver.findElement(By.xpath("//*[@id=\"courtsdropdown\"]")).clear();
                    }
                } else {
                    driver.findElement(By.xpath("//*[@id=\"courtsdropdown\"]")).sendKeys("New York " + MainComboBoxes.selectedCountyMain);
                    driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/div[1]/courtlist/div/courtlistselector/div/div[2]/div/div[2]/div/div/div/div/label/div")).click();
                }
                break;
            case "Texas":
                driver.findElement(By.xpath("//*[@id=\"courtsdropdown\"]")).sendKeys("Texas " + MainComboBoxes.selectedCountyMain + " district");
                Thread.sleep(1000);
                driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/div[1]/courtlist/div/courtlistselector/div/div[2]/div/div[2]/div/div/div/div/label/div")).click();
                Thread.sleep(1000);
                break;
        }
        driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/segmentcontrols/div[1]/div[1]/div[2]/input")).click();
    }
}
