package CourtScraper.Setups.Browser.Captcha;

import CourtScraper.Helpers.ConfigGrabbers;
import com.twocaptcha.TwoCaptcha;

import java.io.FileNotFoundException;

public class CaptchaMain {

    public static TwoCaptcha solver;

    public static void initiateSolver() throws FileNotFoundException {
         solver = new TwoCaptcha(new ConfigGrabbers().apiGrabber("2captcha"));
    }
}
