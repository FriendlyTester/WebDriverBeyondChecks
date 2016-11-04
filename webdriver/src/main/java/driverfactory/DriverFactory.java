package driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
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
                DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
                PhantomJSDriver driver = new PhantomJSDriver(capabilities);
                return driver;
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
        String pathToChromeDriver = System.getProperty("user.dir") + "/src/main/java/driverfactory/chromedriver";
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
        return new ChromeDriver();
    }
}
