package courtscraper.setups.browser;

import courtscraper.StartGUI;
import org.openqa.selenium.firefox.*;

import java.io.FileNotFoundException;

import static courtscraper.helpers.inputprocesses.ProcessConfigInputs.getHeadless;
import static courtscraper.setups.browser.useragent.RandomUserAgent.getRandomUserAgent;

public class Firefox extends StartGUI {

    //This file contains the selenium webdriver and browser launch

    public void FirefoxLaunch() throws FileNotFoundException {

        //System.setProperty("webdriver.gecko.driver", firefoxPath);

        ProfilesIni profileIni = new ProfilesIni();
        FirefoxProfile profile = profileIni.getProfile("BOT RUN");
        profile.setPreference("general.useragent.override", getRandomUserAgent());
        FirefoxOptions options = new FirefoxOptions();

        options.setProfile(profile);

        //options.setCapability("marionette", true);

        //decides if config is start headless
        if (getHeadless()) {options.addArguments("--headless");}

        driver = new FirefoxDriver(options);
        driver.get("https://www.google.com/");
    }
}
