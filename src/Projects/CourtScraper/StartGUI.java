package CourtScraper;

import CourtScraper.Setups.GUI.Interface;
import org.openqa.selenium.WebDriver;

public class StartGUI {

    //interface start

    public static WebDriver driver;

    public static void main( String[] args ) throws InterruptedException {
        try {
            //Initializes interface
            Interface.mainGUI();

            //Flow actually starts in Buttons.java inside of GUI. Inside the startButton function
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
