package CourtScraper.Setups.Browser.Captcha;

import CourtScraper.DataManagement.JSON.JSONGrabbers;
import com.twocaptcha.TwoCaptcha;

import java.io.FileNotFoundException;

public class CaptchaMain {

    public static TwoCaptcha solver;

    public static void initiateSolver() throws FileNotFoundException {
         solver = new TwoCaptcha(new JSONGrabbers().apiGrabber("2captcha"));
    }
}
