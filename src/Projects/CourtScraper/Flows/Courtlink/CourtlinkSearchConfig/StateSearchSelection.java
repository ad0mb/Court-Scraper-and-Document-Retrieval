package CourtScraper.Flows.Courtlink.CourtlinkSearchConfig;

import CourtScraper.Setups.GUI.MainPanelElements.MainComboBoxes;
import CourtScraper.StartGUI;
import org.openqa.selenium.By;

public class StateSearchSelection extends CourtlinkSearchConfigMain {

    public static void searchState(String state) throws InterruptedException {
        switch (state) {
            case "California":
                StartGUI.driver.findElement(By.xpath("//*[@id=\"courtsdropdown\"]")).sendKeys("California " + MainComboBoxes.selectedCounty);
                StartGUI.driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/div[1]/courtlist/div/courtlistselector/div/div[2]/div/div[2]/div/div/div/div/label/div")).click();
                break;
            case "Florida":
                StartGUI.driver.findElement(By.xpath("//*[@id=\"courtsdropdown\"]")).sendKeys("Florida " + MainComboBoxes.selectedCounty);
                StartGUI.driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/div[1]/courtlist/div/courtlistselector/div/div[2]/div/div[2]/div/div/div/div/label/div")).click();
                break;
            case "New York":
                if (MainComboBoxes.selectedCounty.equals("New York BKQ")) {
                    String[] BKQ = {"Bronx", "Kings", "Queens"};
                    for (String burrough : BKQ) {
                        StartGUI.driver.findElement(By.xpath("//*[@id=\"courtsdropdown\"]")).sendKeys("New York " + burrough);
                        StartGUI.driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/div[1]/courtlist/div/courtlistselector/div/div[2]/div/div[2]/div/div/div/div/label/div")).click();
                        StartGUI.driver.findElement(By.xpath("//*[@id=\"courtsdropdown\"]")).clear();
                    }
                } else {
                    StartGUI.driver.findElement(By.xpath("//*[@id=\"courtsdropdown\"]")).sendKeys("New York " + MainComboBoxes.selectedCounty);
                    StartGUI.driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/div[1]/courtlist/div/courtlistselector/div/div[2]/div/div[2]/div/div/div/div/label/div")).click();
                }
                break;
            case "Texas":
                StartGUI.driver.findElement(By.xpath("//*[@id=\"courtsdropdown\"]")).sendKeys("Texas " + MainComboBoxes.selectedCounty + " district");
                Thread.sleep(1000);
                StartGUI.driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/div[1]/courtlist/div/courtlistselector/div/div[2]/div/div[2]/div/div/div/div/label/div")).click();
                Thread.sleep(1000);
                break;
        }
        StartGUI.driver.findElement(By.xpath("/html/body/main/div/ln-courtlinksearchform/div/searchform/div[1]/segmentcontrols/div[1]/div[1]/div[2]/input")).click();
    }
}
