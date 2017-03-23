package test.browser;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.LogManager;

public abstract class BrowserTestBase {

    protected static WebDriver driver;
    protected static Properties browserInfo;
    protected static DesiredCapabilities capabilities;

    @BeforeClass
    public static void beforeClass() {
        // load browser properties
        browserInfo = new Properties();
        try {
            browserInfo.load( new FileInputStream( "browserInfo.properties" ) );
        }
        catch ( IOException e ) {
            e.printStackTrace();
            return;
        }
        // logger setting
        try {
            InputStream inStream = new FileInputStream( "logging.properties" );
            LogManager.getLogManager().readConfiguration( inStream );
        }
        catch ( IOException | SecurityException ex ) {
        }
    }

    @Before
    public void preTest() {
        if ( driver != null ) {
            driver = null;
        }
        initDriver();
    }

    @After
    public void postTest() {
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static Properties getBrowserInfo() {
        return browserInfo;
    }

    abstract protected void initDriver();

}
