/**
 * @author Adam Bouloudene
 * @summary This class contains all of the Folder paths to be used for this program. The updateCountyFolderPath method redefines paths after flow starts to get most up to date state and county information.
 *
 */

package courtscraper.helpers;

import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.selectedCountyMain;
import static courtscraper.setups.gui.mainpanelelements.MainComboBoxes.selectedStateMain;

public class FolderPaths {

    /*----------------------------------------------------------------------------------------------------------------*/

    //project folder
    public final static String COURTLINK_SCRAPER_PATH = "C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper";

    /*----------------------------------------------------------------------------------------------------------------*/

    //folders

        //temp folder
    public final static String TEMP_FOLDER_PATH = COURTLINK_SCRAPER_PATH + "\\Temp";

        //configs folder
    public final static String CONFIGS_FOLDER_PATH = COURTLINK_SCRAPER_PATH + "\\Configs";
    public final static String CAPTCHAS_FOLDER_PATH = CONFIGS_FOLDER_PATH + "\\Captchas";

        //county folder
    public static String COUNTY_FOLDER_PATH;
    public static String DOWNLOADS_FOLDER_PATH;

        //logs folder
    public final static String LOGS_FOLDER_PATH = COURTLINK_SCRAPER_PATH + "\\Logs";
    public final static String CRASH_LOGS_FOLDER_PATH = LOGS_FOLDER_PATH + "\\Crash Logs";
    public final static String PAST_JOBS_FOLDER_PATH = LOGS_FOLDER_PATH + "\\Past Jobs";

    //folders

    /*----------------------------------------------------------------------------------------------------------------*/

    //csv files

        //configs folder
    public final static String PAST_JOBS_CSV_PATH = PAST_JOBS_FOLDER_PATH + "\\PastJobs.csv";
    public final static String ALL_JOBS_CSV_PATH = PAST_JOBS_FOLDER_PATH + "\\AllJobs.csv";

        //county folder
    public static String DOWNLOADED_TEST_CSV_PATH;
    public static String TEMP_CSV_PATH;

    //csv files

    /*----------------------------------------------------------------------------------------------------------------*/

    //json files

        //configs folder
    public final static String CONFIGS_JSON_PATH = CONFIGS_FOLDER_PATH + "\\Configs.json";
    public final static String API_KEYS_JSON_PATH = CONFIGS_FOLDER_PATH + "\\API Keys.json";
    public final static String LOGINS_JSON_PATH = CONFIGS_FOLDER_PATH + "\\logins.json";

    //json files

    /*----------------------------------------------------------------------------------------------------------------*/

    //jpg files

        //captchas folder
    public final static String CAPTCHA_JPG_PATH = CAPTCHAS_FOLDER_PATH + "\\captcha.jpg";

    //jpg files

    /*----------------------------------------------------------------------------------------------------------------*/

    public static void updateCountyFolderPath() {
        //folders
        COUNTY_FOLDER_PATH = COURTLINK_SCRAPER_PATH + "\\States\\" + selectedStateMain + "\\" + selectedCountyMain;
        DOWNLOADS_FOLDER_PATH = COUNTY_FOLDER_PATH + "\\Downloaded";

        //csv files
        DOWNLOADED_TEST_CSV_PATH = COUNTY_FOLDER_PATH + "\\downloadedtest.csv";
        TEMP_CSV_PATH = COUNTY_FOLDER_PATH + "\\temp.csv";
    }
}
