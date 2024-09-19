package courtscraper.setups.browser;

import courtscraper.StartGUI;
import org.openqa.selenium.firefox.*;

import java.io.FileNotFoundException;

import static courtscraper.setups.browser.captcha.CaptchaMain.initiateSolver;
import static courtscraper.setups.browser.useragent.RandomUserAgent.getRandomUserAgent;

public class Firefox extends StartGUI {

    //This file contains the selenium webdriver and browser launch

    public void FirefoxLaunch() throws FileNotFoundException {

        //System.setProperty("webdriver.gecko.driver", firefoxPath);

        //starts 2 captcha solver
        initiateSolver();

        ProfilesIni profileIni = new ProfilesIni();
        FirefoxProfile profile = profileIni.getProfile("BOT RUN");
        profile.setPreference("general.useragent.override", getRandomUserAgent());
        FirefoxOptions options = new FirefoxOptions();

        options.setProfile(profile);

        //options.setCapability("marionette", true);
        //options.addArguments("--headless");

        driver = new FirefoxDriver(options);
        driver.get("https://www.google.com/");
    }
}
