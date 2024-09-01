package Projects.Setups.Browser;

import Projects.StartGUI;
import org.openqa.selenium.firefox.*;

public class Firefox extends StartGUI {

    public void FirefoxLaunch() {

        //System.setProperty("webdriver.gecko.driver", firefoxPath);

        ProfilesIni profileIni = new ProfilesIni();
        FirefoxProfile profile = profileIni.getProfile("BOT RUN");
        FirefoxOptions options = new FirefoxOptions();

        options.setProfile(profile);

        //options.setCapability("marionette", true);
        //options.addArguments("--headless");

        driver = new FirefoxDriver(options);
        driver.get("https://www.google.com/");
    }
}
