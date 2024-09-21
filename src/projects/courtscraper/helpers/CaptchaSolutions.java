package courtscraper.helpers;

import com.twocaptcha.captcha.Normal;
import org.openqa.selenium.By;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import static courtscraper.helpers.FolderPaths.CAPTCHA_JPG_PATH;
import static courtscraper.setups.browser.captcha.CaptchaMain.solver;
import static courtscraper.StartGUI.driver;

public class CaptchaSolutions {

    //File containing captcha methods

    private static Normal normalCaptcha = new Normal();

    public static String solveNormalCaptcha(String xpathString) throws Exception {

        //image saver
        String src = driver.findElement(By.xpath(xpathString)).getAttribute("src");
        BufferedImage bufferedImage = ImageIO.read(new URL(src));
        File outputFile = new File(CAPTCHA_JPG_PATH);
        ImageIO.write(bufferedImage, "jpg", outputFile);

        normalCaptcha.setFile(outputFile);
        normalCaptcha.setNumeric(4);
        normalCaptcha.setCalc(false);
        normalCaptcha.setCaseSensitive(true);

        solver.solve(normalCaptcha);

        outputFile.delete();

        return normalCaptcha.getCode();
    }
}
