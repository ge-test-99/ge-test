package com.goeuro.Tests;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;


@Ignore
public class SeleniumTestBase {

    @Rule
    public TestName name = new TestName();

    WebDriver driver;
    private static String remoteSeleniumURI;
    private static String browser;

    /**
     * Constructs a new {@link RemoteWebDriver} instance and sets {@link #browser} capability,
     * @throws Exception if an error occurs during the creation of the {@link RemoteWebDriver} instance.
     */
    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        this.driver = new RemoteWebDriver(new URL(remoteSeleniumURI), capabilities);
    }

    /**
     * Saves screenshot with {@param #fileName}
     * @param fileName should include extension
     * @throws IOException in case of file i/o error
     */
    private void saveScreenshot(String fileName) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(fileName));
    }

    @After
    public void tearDown() throws Exception {
        saveScreenshot(String.format("screenshots/%s.png", name.getMethodName()));
        driver.quit();
    }

    /**
     * Gets environment settings for {@link #remoteSeleniumURI} and {@link #browser} with fallback to defaults
     */
    @BeforeClass
    public static void setupClass() {
        remoteSeleniumURI = System.getenv("SELENIUM_URI");
        if(remoteSeleniumURI == null){
             remoteSeleniumURI = "http://127.0.0.1:4444/wd/hub";
        }
        browser = System.getenv("SELENIUM_BROWSER");
        if(browser == null){
            browser = "chrome";
        }
    }
}
