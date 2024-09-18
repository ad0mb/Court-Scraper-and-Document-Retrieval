package CourtScraper.Helpers;

import com.twocaptcha.captcha.Normal;
import org.openqa.selenium.By;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import static CourtScraper.Setups.Browser.Captcha.CaptchaMain.solver;
import static CourtScraper.StartGUI.driver;

public class CaptchaSolutions {

    //File containing captcha methods

    private static Normal normalCaptcha = new Normal();

    public static String solveNormalCaptcha(String xpathString) throws Exception {

        //image saver
        String src = driver.findElement(By.xpath(xpathString)).getAttribute("src");
        BufferedImage bufferedImage = ImageIO.read(new URL(src));
        File outputFile = new File("C:\\Users\\" + System.getenv("USERNAME") + "\\Desktop\\Courtlink Scraper\\Configs\\Captchas\\captcha.jpg");
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
