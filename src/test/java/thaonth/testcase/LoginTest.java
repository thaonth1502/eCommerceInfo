package thaonth.testcase;

import org.testng.annotations.Test;
import thaonth.common.BaseTest;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginSuccess(){
        getLoginPage().loginCMS("admin@example.com", "123456");
        getLoginPage().verifyLoginSuccess();
    }

    @Test
    public void testLoginFailWithEmailBlank(){
        getLoginPage().loginCMS("","123456");
        getLoginPage().verifyLoginFailWithEmailBlank("Please fill out this field.");
    }

    @Test
    public void testLoginFailWithPasswordBlank(){
        getLoginPage().loginCMS("admin@example.com","");
        getLoginPage().verifyLoginFailWithPasswordBlank("Please fill out this field.");
    }

    @Test
    public void testLoginFailWithPasswordInvalid(){
        getLoginPage().loginCMS("admin@example.com","12345678");
        getLoginPage().verifyLoginFail("Invalid login credentials");
    }

    @Test
    public void testLoginFailWithEmailInvalid(){
        getLoginPage().loginCMS("admin1@example.com","123456");
        getLoginPage().verifyLoginFail("Invalid login credentials");
    }

}
