package courtscraper;

import courtscraper.helpers.Logger;

import java.io.IOException;

import static courtscraper.setups.filesetup.VerifyFilesMain.verifyMain;
import static courtscraper.setups.gui.Interface.mainGUI;

public class StartGUI {

    //interface start

    public static Logger runLogger = new Logger();

    public static void main( String[] args ) throws InterruptedException, IOException {

        try {
            verifyMain();


            mainGUI(); //Initializes interface

            verifyMain();

            //Flow actually starts in Buttons.java inside of GUI. Inside the startButton function
        } catch (Exception e) {
            runLogger.logError(e);
            e.printStackTrace();
        }
    }

}
