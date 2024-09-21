package courtscraper.helpers;

import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.selectedCountyMain;
import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.selectedStateMain;

public class FolderPaths {

    //project folder
    public final static String COURTLINK_SCRAPER_PATH = "C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper";

    //folders
    public final static String DOWNLOADS_FOLDER_PATH = COURTLINK_SCRAPER_PATH + "\\States\\" + selectedStateMain + "\\" + selectedCountyMain + "\\Downloaded";
    public final static String TEMP_FOLDER_PATH = COURTLINK_SCRAPER_PATH + "\\Temp";
    public final static String CONFIGS_FOLDER_PATH = COURTLINK_SCRAPER_PATH + "\\Configs";
    public final static String COUNTY_FOLDER_PATH = COURTLINK_SCRAPER_PATH + "\\States\\" + selectedStateMain + "\\" + selectedCountyMain;
    public final static String CAPTCHAS_FOLDER_PATH = CONFIGS_FOLDER_PATH + "\\Captchas";


    //csv files
    public final static String PAST_JOBS_CSV_PATH = CONFIGS_FOLDER_PATH + "\\PastJobs.csv";
    public final static String DOWNLOADED_TEST_CSV_PATH = COUNTY_FOLDER_PATH + "\\downloadedtest.csv";
    public final static String TEMP_CSV_PATH = COUNTY_FOLDER_PATH + "\\temp.csv";

    //json files
    public final static String CONFIGS_JSON_PATH = CONFIGS_FOLDER_PATH + "\\Configs.json";
    public final static String API_KEYS_JSON_PATH = CONFIGS_FOLDER_PATH + "\\API Keys.json";
    public final static String LOGINS_JSON_PATH = CONFIGS_FOLDER_PATH + "\\logins.json";

    //jpg files
    public final static String CAPTCHA_JPG_PATH = CAPTCHAS_FOLDER_PATH + "\\captcha.jpg";
}
