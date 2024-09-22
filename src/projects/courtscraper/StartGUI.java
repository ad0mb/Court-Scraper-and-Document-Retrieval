package courtscraper;

import static courtscraper.setups.filesetup.VerifyFilesMain.verifyMain;
import static courtscraper.setups.gui.Interface.mainGUI;

public class StartGUI {

    //interface start



    public static void main( String[] args ) throws InterruptedException {
        try {
            verifyMain();

            //Initializes interface
            mainGUI();

            verifyMain();

            //Flow actually starts in Buttons.java inside of GUI. Inside the startButton function
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
