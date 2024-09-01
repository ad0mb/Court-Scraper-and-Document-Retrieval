package Projects;

import Projects.Setups.GUI.Interface;
import org.openqa.selenium.WebDriver;

public class StartGUI {

    public static WebDriver driver;

    public static void main( String[] args ) throws InterruptedException {

        //Initializes interface
        Interface.mainGUI();

        //Flow actually starts in Buttons.java inside of GUI. Inside the startButton function

    }

}
