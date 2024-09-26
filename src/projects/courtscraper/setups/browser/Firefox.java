package courtscraper.setups.browser;

import courtscraper.FlowStart;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;

import java.io.File;
import java.io.FileNotFoundException;

import static courtscraper.helpers.FolderPaths.TEMP_FOLDER_PATH;
import static courtscraper.helpers.guiinputprocessors.ProcessConfigInputs.getHeadless;
import static courtscraper.setups.browser.useragent.RandomUserAgent.getRandomUserAgent;

public class Firefox extends FlowStart {

    //This file contains the selenium webdriver and browser launch

    private File tempFolder = new File(TEMP_FOLDER_PATH);

    public void FirefoxLaunch() throws FileNotFoundException {

        ProfilesIni profileIni = new ProfilesIni();

        FirefoxProfile profile = profileIni.getProfile("botrun");
        profile.setPreference("general.useragent.override", getRandomUserAgent());

        //sets download path on firefox launch
        profile.setPreference("browser.download.dir", tempFolder.getAbsolutePath());
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.useDownloadDir", true);

        //makes sure pdfs are saved
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
        profile.setPreference("pdfjs.disabled", true);
        FirefoxOptions options = new FirefoxOptions();

        options.setProfile(profile);

        //options.setCapability("marionette", true);

        //decides if config is start headless
        if (getHeadless()) {options.addArguments("--headless");}

        driver = new FirefoxDriver(options);
        driver.get("https://www.google.com/");
    }
}
