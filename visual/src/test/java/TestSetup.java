import com.applitools.eyes.Eyes;
import driverfactory.DriverFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

/**
 * Created by richard on 21/07/2016.
 */
public class TestSetup
{
    protected WebDriver driver;
    protected String baseUrl;
    protected Eyes eyes;

    @Before
    public void SetUp()
    {
        driver = new DriverFactory().create();
        baseUrl = "http://52.17.197.56:8080/bugzilla/";
        eyes = new Eyes();
        eyes.setApiKey("prEpqHPkYz6R14a0jJw8CP8WzqPSN1vt4qKPUxbrhRk110");
    }

    @After
    public void TearDown()
    {
        eyes.close();
        driver.quit();
    }

}
