/**
 * @author Adam Bouloudene
 * @summary This class contains the Config input grabbers which are designed to grab the settings configured in the settings panel for different purposes throughout the code.
 *
 */

package courtscraper.helpers.guiinputprocessors;

import courtscraper.datamanagement.json.JSONGrabbers;

import java.io.FileNotFoundException;

public class ProcessConfigInputs {

    public static String getCaptcha() throws FileNotFoundException {
        return new JSONGrabbers().configGrabber("captcha");
    }

    public static Boolean getHeadless() throws FileNotFoundException {
        return Boolean.parseBoolean(new JSONGrabbers().configGrabber("headless"));
    }

    public static Boolean getManualSearch() throws FileNotFoundException {
        return Boolean.parseBoolean(new JSONGrabbers().configGrabber("manual search input"));
    }
}
