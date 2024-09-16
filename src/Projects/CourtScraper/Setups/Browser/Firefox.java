package CourtScraper.Setups.Browser;

import CourtScraper.Setups.Browser.UserAgent.RandomUserAgent;
import CourtScraper.StartGUI;
import org.openqa.selenium.firefox.*;

public class Firefox extends StartGUI {

    //This file contains the selenium webdriver and browser launch

    public void FirefoxLaunch() {

        //System.setProperty("webdriver.gecko.driver", firefoxPath);

        ProfilesIni profileIni = new ProfilesIni();
        FirefoxProfile profile = profileIni.getProfile("BOT RUN");
        profile.setPreference("general.useragent.override", RandomUserAgent.getRandomUserAgent());
        FirefoxOptions options = new FirefoxOptions();

        options.setProfile(profile);

        //options.setCapability("marionette", true);
        //options.addArguments("--headless");

        driver = new FirefoxDriver(options);
        driver.get("https://www.google.com/");
    }
}
