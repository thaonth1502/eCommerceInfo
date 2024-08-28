package thaonth.pages;

import org.openqa.selenium.By;
import org.testng.Assert;
import thaonth.constants.DataConfig;
import thaonth.drivers.DriverManager;
import thaonth.keywords.WebUI;

public class LoginPage {

    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By btnLogin = By.xpath("//button[normalize-space()='Login']");
    private By headerLogin = By.xpath("//h1[normalize-space()='Welcome to Active eCommerce CMS']");
    private By errorMessage = By.xpath("//span[@data-notify='message']");

    public void enterEmail(String email){
        WebUI.inputElement(inputEmail, email);
    }

    public void enterPassword (String password){
        WebUI.inputElement(inputPassword, password);
    }

    public void clickLoginButton(){
        WebUI.waitForElementClickable(btnLogin);
        WebUI.clickElement(btnLogin);
    }

    public DashboardPage loginCMS(String email, String password){
        WebUI.openURL(DataConfig.URL);
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
        return new DashboardPage();
    }

    public void verifyLoginFail(String message){
        Assert.assertTrue(WebUI.elementIsDisplay(errorMessage),"FAIL!!! Error message not display");
        WebUI.assertEquals(WebUI.getElementText(errorMessage),message, "FAIL!!! The content message not match");
    }

    public void verifyLoginSuccess(){
       String currentURL =  DriverManager.getDriver().getCurrentUrl();
        WebUI.assertEquals(currentURL, "https://cms.anhtester.com/admin", "FAIL!!! Redirect to url wrong");
    }

    public void verifyLoginFailWithEmailBlank(String message){
        String validateMessage = WebUI.getElementAttribute(inputEmail,"validationMessage");
        WebUI.assertEquals(validateMessage, message, "FAIL!!! The content error message not match");
    }

    public void verifyLoginFailWithPasswordBlank(String message){
        String validateMessage = WebUI.getElementAttribute(inputPassword,"validationMessage");
        WebUI.assertEquals(validateMessage, message, "FAIL!!! The content error message not match");
    }

}
