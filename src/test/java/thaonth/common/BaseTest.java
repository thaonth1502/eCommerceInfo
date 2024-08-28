package thaonth.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import thaonth.constants.DataConfig;
import thaonth.drivers.DriverManager;
import thaonth.pages.DashboardPage;
import thaonth.pages.LoginPage;

public class BaseTest {

    WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    public DashboardPage getDashboardPage() {
        if (dashboardPage == null){
            dashboardPage = new DashboardPage();
        }
        return dashboardPage;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null){
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    @BeforeMethod
    public void createDriver(){
        driver = setupDriver(DataConfig.BROWSER);
        DriverManager.setDriver(driver);
    }

    @AfterMethod
    public void closeDriver(){
        DriverManager.quit();
    }

    private WebDriver setupDriver(String browserName) {
        switch (browserName.trim().toLowerCase()){
            case "chrome":
                driver = initChromeDriver();
                break;
            case "edge":
                driver = initEdgeDriver();
                break;
            case "firefox":
                driver = initFirefoxDriver();
                break;
            default:
                System.out.println("Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver();
        }
        return driver;
    }

    private WebDriver initChromeDriver(){
        System.out.println("Launching Chrome browser .....");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initEdgeDriver(){
        System.out.println("Launching Edge browser .....");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }
    private WebDriver initFirefoxDriver(){
        System.out.println("Launching Firefox browser .....");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }
}
