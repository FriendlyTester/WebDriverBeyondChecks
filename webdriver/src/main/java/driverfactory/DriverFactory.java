package driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by richard on 17/07/2016.
 */
public class DriverFactory
{
    public WebDriver create()
    {
        try{
            if(System.getenv("browser").equals("phantomjs")){
                String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36";

                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX + "userAgent", userAgent);

                return new PhantomJSDriver(caps);
            } else {
                ChromeDriver driver = generateChromeDriver();
                return driver;
            }
        } catch (NullPointerException e){
            ChromeDriver driver = generateChromeDriver();
            return driver;
        }

    }

    private ChromeDriver generateChromeDriver(){

        //If you don't have ChromeDriver on your path, then uncomment the lines relevant to your OS

        //OSX
        //String pathToChromeDriver = System.getProperty("user.dir") + "/src/main/java/driverfactory/chromedriver";
        //System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

        //Windows
        String pathToChromeDriver = System.getProperty("user.dir") + "/src/main/java/driverfactory/windows/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

        return new ChromeDriver();
    }
}
