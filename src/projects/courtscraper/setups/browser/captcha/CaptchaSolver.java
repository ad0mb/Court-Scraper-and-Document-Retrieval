package courtscraper.setups.browser.captcha;

import com.twocaptcha.TwoCaptcha;
import com.twocaptcha.captcha.Normal;
import courtscraper.datamanagement.json.JSONGrabbers;
import org.openqa.selenium.By;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import static courtscraper.FlowStart.driver;
import static courtscraper.helpers.FolderPaths.CAPTCHA_JPG_PATH;

public class CaptchaSolver {

    //this class contains all the captcha solving methods

    private TwoCaptcha solver;
    private Normal normalCaptcha;

    public CaptchaSolver() throws FileNotFoundException {
        this.solver = new TwoCaptcha(new JSONGrabbers().apiGrabber("2captcha"));
        this.normalCaptcha = new Normal();
    }

    public String solveNormalCaptcha(String xpathString) throws Exception {

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
