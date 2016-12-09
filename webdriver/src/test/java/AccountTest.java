import org.junit.Assert;
import org.junit.Test;
import pageobjects.CreateAccountPage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by richard on 21/07/2016.
 */
public class AccountTest extends TestSetup
{
    @Test
    public void CreateANewAccount() throws ParseException
    {
        driver.navigate().to(baseUrl + "createaccount.cgi");
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);

        String randomEmail = "test" + new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date()) + "@test.com";

        createAccountPage.PopulateEmailAddress(randomEmail);
        createAccountPage = createAccountPage.ClickSend();
        Assert.assertThat(createAccountPage.ReadConfirmationMessage(), is(equalTo("A confirmation email has been sent containing a link to continue creating an account. The link will expire if an account is not created within 3 days.")));
    }

    @Test
    public void CantCreateAccountWithInvalidEmail() throws ParseException
    {
        driver.navigate().to(baseUrl + "createaccount.cgi");
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        createAccountPage.PopulateEmailAddress("test.com");
        createAccountPage = createAccountPage.ClickSend();
        // Depending on browser behaviour the title returned is different because the behaviour of the popup validation on the
        // email submission form varies
        Assert.assertThat(createAccountPage.ReadTitle(), anyOf(is("Bugzilla – Create a new Bugzilla accounts"), is("Bugzilla – Invalid Email Address")));
    }

    @Test
    public void HaveToWaitTenMinutesToCreateAccount()
    {
        driver.navigate().to(baseUrl + "createaccount.cgi");
        CreateAccountPage createAccountPage = new CreateAccountPage(driver);
        String randomEmail = "test" + new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date()) + "@test.com";
        createAccountPage.PopulateEmailAddress(randomEmail);
        createAccountPage = createAccountPage.ClickSend();
        driver.navigate().to(baseUrl + "createaccount.cgi");
        createAccountPage.PopulateEmailAddress(randomEmail);
        createAccountPage = createAccountPage.ClickSend();
        Assert.assertThat(createAccountPage.ReadErrorMessage(), is(equalTo("You have requested an account token too recently to request another. Please wait 10 minutes then try again.")));
    }
}
