package CourtScraper;

import org.openqa.selenium.WebDriver;

import static CourtScraper.Setups.GUI.Interface.mainGUI;

public class StartGUI {

    //interface start

    public static WebDriver driver;

    public static void main( String[] args ) throws InterruptedException {
        try {
            //Initializes interface
            mainGUI();

            //Flow actually starts in Buttons.java inside of GUI. Inside the startButton function
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
