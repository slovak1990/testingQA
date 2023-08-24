package testovoe08.tests;

import org.junit.Test;
import testovoe08.Properties;
import testovoe08.TestBase;
import testovoe08.model.UserData;
import testovoe08.steps.LoginStep;

import static org.hamcrest.MatcherAssert.assertThat;

public class Login extends TestBase {
    LoginStep loginStep = new LoginStep();

    String login = Properties.getInstance().getLogin1();
    String password = Properties.getInstance().getPassword1();

    @Test
    public void testLogin() {
        loginStep.login(new UserData().withLogin(login).withPassword(password));
        //assertThat("Вход не выполнен!", );
    }
}